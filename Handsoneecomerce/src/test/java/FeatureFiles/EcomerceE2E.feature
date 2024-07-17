Feature: E2E of Rahulshetty ecommerece application


Scenario Outline: Verify User Authenticate the credentials and get the access token.
  Given User Add valid Credentials "<userEmail>" and "<userPassword>" in the body payload.
  When  User Send the "<Method>" Request to "EcomereceloginApi" resource with the credentials.
  Then  User get the Status code in the response body is "200".
  And   User get the "status" in the response body is "OK".
Examples:
|userEmail |userPassword|Method|
|srikanthkarthik25@gmail.com|Password@9676|Post|



Scenario: Verify User able to add the product in the Ecommerece application.
 Given  User provide valid product deatils in the form-data like below.
 |productName       |qwerty            |
 |productAddedBy    |{{userId}}        |
 |productCategory   |fashion           |
 |productSubCategory|shirts            |
 |productPrice      |11500             |
 |productDescription|Addias Originals  |
 |productFor        |women             |
 |productImage     | D:\QA Testing\user\RestWorkspace\Handsoneecomerce\ProductImages\r2.jpg|

 When  User Send the "Post" with resource "EcomereceAddproductApi".
 Then  User get the Status code in the response body is "201" created.
 And  User get respective "productId" and "message" in the response body.
 
 
 
Scenario Outline: Verify that user Able to place the order.
    Given User Added Valid bodypayload like country "<country>".
    When  User send the Post "Post" method along with resource "EcommereceorderplaceApi".
    Then  User get the message "message" in the response body.
    And   User get the orders id "orders" in the response body.
Examples:
  |country|
  |India |
  
 
Scenario: Verfiy user able to delete the placed order successfully.
    Given Add valid productid in the path parameters.
    When  User send the Delete "DELETE" with resource "Ecomerecedeleteapi".
    Then user got the message "message" is "Product Deleted Successfully" in the response.

 