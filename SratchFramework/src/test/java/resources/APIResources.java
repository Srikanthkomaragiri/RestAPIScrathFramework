package resources;

public enum APIResources {

	
	AddPlaceApi("/maps/api/place/add/json"),
	GETPlaceApi("maps/api/place/get/json"),
	DELETEPlaceApi("maps/api/place/delete/json");
	
	private String  resource;
	 APIResources(String Resource)
	{
		this.resource = Resource;
	}
	
	public String getresource()
	{
		return resource;
	}
	
	
	
}
