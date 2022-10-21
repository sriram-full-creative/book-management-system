package com.fullcreative.bms.utilities;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;

public class GoogleCloudStorageUtilities {
	/** Google Cloud Storage Methods **/

	/**
	 * <p>
	 * Uploads a file to Cloud Storage and returns the uploaded file's URL.
	 * </p>
	 * 
	 * @param fileName
	 * @param fileInputStream
	 * @return String
	 */
	public static String uploadToCloudStorage(String fileName, String fileFormat, InputStream fileInputStream) {
		String projectId = "book-management-system-362310";
		String bucketName = "book-management-system-362310.appspot.com";
		String publicUrl = "https://storage.googleapis.com/" + bucketName + "/" + fileName;
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, fileName);
		BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/" + fileFormat)
				.setCacheControl("no-store")
				.setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER),
						Acl.of(new Acl.Project(Acl.Project.ProjectRole.OWNERS, projectId), Acl.Role.OWNER))))
				.build();
		@SuppressWarnings({ "deprecation", "unused" })
		Blob blob = storage.create(blobInfo, fileInputStream);
		return publicUrl;
	}

	/**
	 * <p>
	 * Uploads a file to Cloud Storage and returns the uploaded file's URL.
	 * </p>
	 * 
	 * @param fileName
	 * @param fileInputStream
	 * @return String
	 */
	@SuppressWarnings("deprecation")
	public static String updateImageInCloudStorage(String fileName, String fileFormat, InputStream fileInputStream) {
		String projectId = "book-management-system-362310";
		String bucketName = "book-management-system-362310.appspot.com";
		String publicUrl = "https://storage.googleapis.com/" + bucketName + "/" + fileName;
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, fileName);
		Blob blob = storage.get(blobId);
		blob = storage.get(blobId);
		if (blob != null) {
			storage.delete(bucketName, fileName);
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/" + fileFormat)
					.setCacheControl("no-store")
					.setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER),
							Acl.of(new Acl.Project(Acl.Project.ProjectRole.OWNERS, projectId), Acl.Role.OWNER))))
					.build();
			blob = storage.create(blobInfo, fileInputStream);
			return publicUrl;
		} else {
			BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("image/" + fileFormat)
					.setCacheControl("no-store")
					.setAcl(new ArrayList<>(Arrays.asList(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER),
							Acl.of(new Acl.Project(Acl.Project.ProjectRole.OWNERS, projectId), Acl.Role.OWNER))))
					.build();
			blob = storage.create(blobInfo, fileInputStream);
			return publicUrl;
		}
	}

	/**
	 * <p>
	 * Deletes the coverImage of the book when the book is deleted.
	 * </p>
	 * 
	 * @param fileName
	 * @return boolean
	 */
	@SuppressWarnings("unused")
	public static boolean deleteImageInCloudStorage(String fileName) {
		String projectId = "book-management-system-362310";
		String bucketName = "book-management-system-362310.appspot.com";
		Storage storage = StorageOptions.newBuilder().setProjectId(projectId).build().getService();
		BlobId blobId = BlobId.of(bucketName, fileName);
		try {
			Blob blob = storage.get(blobId);
			blob = storage.get(blobId);
			if (blob != null) {
				storage.delete(bucketName, fileName);
				return true;
			} else if (blob == null) {
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Caught in deleteImageInCloudStorage Method");
			return false;
		}
		return false;
	}
}
