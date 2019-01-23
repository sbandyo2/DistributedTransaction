# DistributedTransaction
Logic- the supplier service would be invoked to add a suplier http://localhost:9102/supplierservice/add
This will  invoke save operation corresponding to a transaction and will call the validation service to perform the validation.
You need to have Rabbit MQ to test this out, check the application.properties for Rabbit MQ server details , change it if required
If the supplier is valid the validation service will send a Commit  event on the same transaction ID else will send Rollback event.

All the events will be send to Transaction Que listened by the transaction Manager  to manage a transaction across distributed microservices

JSON for testing Successful Commit Case
{
 "supp_Partner_Id":2851,
 "vendorID":"123456789",
 "locationID":"123456789",
 "name":"Supplier Test 1",
 "city":"",
 "street":"",
 "postalcode":"",
 "region":"NY", 
 "country":"US",
 "phone":"",
 "fax":"",
 "emailAddress":""
}

JSON for testing ROLLBACK  Case

{
 "supp_Partner_Id":2851,
 "vendorID":"123456789",
 "locationID":"123456789",
 "name":"Supplier Test 1",
 "city":"",
 "street":"",
 "postalcode":"",
 "region":"IL", 
 "country":"US",
 "phone":"",
 "fax":"",
 "emailAddress":""
}

Please note if you need to have access to the DB configured in Transaction manager else you need to configure your own DB in the application.properties file of teansaction manager
