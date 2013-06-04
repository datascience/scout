
package com.rdksys.oai.repository;

/**
 * @author David Uvalle, david.uvalle@gmail.com
 * @version 0.1
 * 
 */
public class MetadataFormat {
	private String metadataPrefix;
	private String schema;
	private String metadataNamespace;
	public String getMetadataPrefix() {
		return metadataPrefix;
	}
	public void setMetadataPrefix(String metadataPrefix) {
		this.metadataPrefix = metadataPrefix;
	}
	public String getSchema() {
		return schema;
	}
	public void setSchema(String schema) {
		this.schema = schema;
	}
	public String getMetadataNamespace() {
		return metadataNamespace;
	}
	public void setMetadataNamespace(String metadataNamespace) {
		this.metadataNamespace = metadataNamespace;
	}
}
