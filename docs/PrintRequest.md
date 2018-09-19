
# PrintRequest

## Properties
Name | Type | Description | Notes
------------ | ------------- | ------------- | -------------
**device** | **String** | The name of the target device used by the API. The device will be setup prior to the sale but allows targeting of multiple devices. | 
**identifier** | **String** | Must include an identifier for a transaction that has been carried out on the device | 
**type** | **String** | The instruction prints the cardholder copy of the receipt by default. If you want to reprint the merchant copy, append “type”:”merchant” to the body of the request. |  [optional]



