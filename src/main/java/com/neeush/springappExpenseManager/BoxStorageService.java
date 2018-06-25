package com.neeush.springappExpenseManager;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;

import java.io.InputStream;
import java.io.Reader;
import java.net.URL;
import java.nio.file.Path;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.box.sdk.BoxConfig;
import com.box.sdk.BoxDeveloperEditionAPIConnection;
import com.box.sdk.BoxFile;
import com.box.sdk.BoxFolder;
import com.box.sdk.IAccessTokenCache;
import com.box.sdk.InMemoryLRUAccessTokenCache;
import com.box.sdk.ProgressListener;

@Service
public class BoxStorageService  implements StorageService{

	@Value("${custom.box.folderid}")
	private String boxRepositoryId;
	
	@Value("${custom.box.configfile}")
	private String boxConfigFileLocation;
	
	
	BoxDeveloperEditionAPIConnection client =null;
	
	@Autowired
	ExpenseRepository expRepo;
	
	
	
	@Override
	public void init(){
		//Check the validity  of the box token
		
		//if expired request for a new token
		//Request via API or BOX SDK's, we are currently using box api;'s
		try {
		// Read config file into Box Config object
		Reader reader = new FileReader(boxConfigFileLocation);
	
		BoxConfig boxConfig = BoxConfig.readFrom(reader);
	
			
		// Set cache info
		int MAX_CACHE_ENTRIES = 100;
		IAccessTokenCache accessTokenCache = new 
			  InMemoryLRUAccessTokenCache(MAX_CACHE_ENTRIES);

		// Create new app enterprise connection object
		client = BoxDeveloperEditionAPIConnection.getAppEnterpriseConnection(boxConfig, accessTokenCache);
		System.out.println( client.getUserAgent());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public String store(MultipartFile file) {
	
		// Set upload values
init();
		
		String folderId = boxRepositoryId;

		// Select Box folder
		BoxFolder folder = new BoxFolder(client, folderId);

		// Upload file
		System.out.println("API :"+folder.getAPI());

		String fileName = file.getOriginalFilename();
		
		 try (InputStream inputStream = file.getInputStream()) {
            
			 BoxFile.Info newFileInfo = folder.uploadFile(inputStream, fileName);
			 BoxFile boxFile = new BoxFile(client, newFileInfo.getID());
			 return boxFile.getDownloadURL().toString();
         }
		  catch(Exception e) {
			  
			  
			e.printStackTrace();  
		  }
		 return "NA";
	}

	@Override
	public Stream<Path> loadAll() {
		
	
		// Select Box folder
		//BoxFolder folder = new BoxFolder(client, boxRepositoryId);
	
		BoxFile file = new BoxFile(client, "id");
		BoxFile.Info info = file.getInfo();

		FileOutputStream stream;
		try {
			stream = new FileOutputStream(info.getName());
		
		// Provide a ProgressListener to monitor the progress of the download.
		file.download(stream, new ProgressListener() {
		    public void onProgressChanged(long numBytes, long totalBytes) {
		        double percentComplete = numBytes / totalBytes;
		    }
		});
		stream.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

	@Override
	public String load(String filename) {
		
		init();
		BoxFile file = new BoxFile(client, filename);
		BoxFile.Info info = file.getInfo();

		FileOutputStream stream = null;
		String downloadURL = "";
		try {
			stream = new FileOutputStream(info.getName());
			URL url = file.getDownloadURL();
			
			if(url!=null) {
				
				downloadURL=url.toString();
			}
			
			System.out.println("URL ::@@@@@@@@@ "+ url.toString());
		
		// Provide a ProgressListener to monitor the progress of the download.
		file.download(stream, new ProgressListener() {
		    public void onProgressChanged(long numBytes, long totalBytes) {
		        double percentComplete = numBytes / totalBytes;
		    }
		});
		stream.close();
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return downloadURL;
	}

	@Override
	public Resource loadAsResource(String filename) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getBatches() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void store(MultipartFile file, String appName, String jobName) {
		// TODO Auto-generated method stub

	}

}
