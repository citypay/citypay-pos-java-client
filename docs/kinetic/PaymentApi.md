# PaymentApi

All URIs are relative to *https://ip-XXX-XXX-XXX-XXX.devices.kineticsmart.com/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**closeJsonPost**](PaymentApi.md#closeJsonPost) | **POST** /close.json | When this instruction is sent, the reconciliation process will be started and the transactions for the day closed. Important, no confirmation is prompted on the device.
[**printJsonPost**](PaymentApi.md#printJsonPost) | **POST** /print.json | Reprint a merchant or customer receipt for a transaction that exists on the device (i.e. has not been cleared by End of Day process).
[**refundJsonPost**](PaymentApi.md#refundJsonPost) | **POST** /refund.json | Initiate a refund transaction on the device.
[**reversalJsonPost**](PaymentApi.md#reversalJsonPost) | **POST** /reversal.json | Initiate a reversal on the device.
[**saleJsonPost**](PaymentApi.md#saleJsonPost) | **POST** /sale.json | Initiate a sale transaction on the device.
[**transactionJsonGet**](PaymentApi.md#transactionJsonGet) | **GET** /transaction.json | Request the status of a transaction in progress or a complete transaction using the uuid as the reference. Depending on the state of the transaction, the response will indicate if it is not found, in progress (and the current stage in the transaction workflow) or complete (with all the disarmed transaction data).


<a name="closeJsonPost"></a>
# **closeJsonPost**
> CloseResponse closeJsonPost()

When this instruction is sent, the reconciliation process will be started and the transactions for the day closed. Important, no confirmation is prompted on the device.

### Example
```java
// Import classes:
//import com.citypay.invoker.ApiException;
//import com.citypay.pos.api.kinetic.PaymentApi;


PaymentApi apiInstance = new PaymentApi();
try {
    CloseResponse result = apiInstance.closeJsonPost();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#closeJsonPost");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**CloseResponse**](CloseResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="printJsonPost"></a>
# **printJsonPost**
> SuccessResponse printJsonPost(body)

Reprint a merchant or customer receipt for a transaction that exists on the device (i.e. has not been cleared by End of Day process).

### Example
```java
// Import classes:
//import com.citypay.invoker.ApiException;
//import com.citypay.pos.api.kinetic.PaymentApi;


PaymentApi apiInstance = new PaymentApi();
PrintRequest body = new PrintRequest(); // PrintRequest | 
try {
    SuccessResponse result = apiInstance.printJsonPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#printJsonPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**PrintRequest**](PrintRequest.md)|  |

### Return type

[**SuccessResponse**](SuccessResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="refundJsonPost"></a>
# **refundJsonPost**
> SaleResponse refundJsonPost(body)

Initiate a refund transaction on the device.

### Example
```java
// Import classes:
//import com.citypay.invoker.ApiException;
//import com.citypay.pos.api.kinetic.PaymentApi;


PaymentApi apiInstance = new PaymentApi();
SaleRequest body = new SaleRequest(); // SaleRequest | 
try {
    SaleResponse result = apiInstance.refundJsonPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#refundJsonPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SaleRequest**](SaleRequest.md)|  |

### Return type

[**SaleResponse**](SaleResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="reversalJsonPost"></a>
# **reversalJsonPost**
> SaleResponse reversalJsonPost(body)

Initiate a reversal on the device.

### Example
```java
// Import classes:
//import com.citypay.invoker.ApiException;
//import com.citypay.pos.api.kinetic.PaymentApi;


PaymentApi apiInstance = new PaymentApi();
ReversalRequest body = new ReversalRequest(); // ReversalRequest | 
try {
    SaleResponse result = apiInstance.reversalJsonPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#reversalJsonPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ReversalRequest**](ReversalRequest.md)|  |

### Return type

[**SaleResponse**](SaleResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="saleJsonPost"></a>
# **saleJsonPost**
> SaleResponse saleJsonPost(body)

Initiate a sale transaction on the device.

### Example
```java
// Import classes:
//import com.citypay.invoker.ApiException;
//import com.citypay.pos.api.kinetic.PaymentApi;


PaymentApi apiInstance = new PaymentApi();
SaleRequest body = new SaleRequest(); // SaleRequest | 
try {
    SaleResponse result = apiInstance.saleJsonPost(body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#saleJsonPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**SaleRequest**](SaleRequest.md)|  |

### Return type

[**SaleResponse**](SaleResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="transactionJsonGet"></a>
# **transactionJsonGet**
> TransactionResult transactionJsonGet(uuid)

Request the status of a transaction in progress or a complete transaction using the uuid as the reference. Depending on the state of the transaction, the response will indicate if it is not found, in progress (and the current stage in the transaction workflow) or complete (with all the disarmed transaction data).

### Example
```java
// Import classes:
//import com.citypay.invoker.ApiException;
//import com.citypay.pos.api.kinetic.PaymentApi;


PaymentApi apiInstance = new PaymentApi();
String uuid = "uuid_example"; // String | The unique identifier of the transaction
try {
    TransactionResult result = apiInstance.transactionJsonGet(uuid);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling PaymentApi#transactionJsonGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **uuid** | **String**| The unique identifier of the transaction |

### Return type

[**TransactionResult**](TransactionResult.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

