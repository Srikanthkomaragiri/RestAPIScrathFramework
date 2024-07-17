package Resources;

public enum APIResources {

	
	EcomereceloginApi("api/ecom/auth/login"),
	EcomereceAddproductApi("api/ecom/product/add-product"),
	EcommereceorderplaceApi("api/ecom/order/create-order"),
	Ecomerecedeleteapi("/api/ecom/product/delete-product/{pid}");
	private String resource;

	APIResources(String Resource){
		this.resource = Resource;
	}
  	
	public String getresource()
	{
		return resource;
	}
}
