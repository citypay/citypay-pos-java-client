# DeviceApi

All URIs are relative to *https://ip-XXX-XXX-XXX-XXX.devices.kineticsmart.com/api/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**infoJsonGet**](DeviceApi.md#infoJsonGet) | **GET** /info.json | Request information about the device.
[**pingJsonGet**](DeviceApi.md#pingJsonGet) | **GET** /ping.json | Simple function to detect the presence of the device on the network. Typically, only used for system setup validation and testing.


<a name="infoJsonGet"></a>
# **infoJsonGet**
> InfoResponse infoJsonGet()

Request information about the device.

### Example
```java
// Import classes:
//import com.citypay.invoker.ApiException;
//import com.citypay.pos.api.kinetic.DeviceApi;


DeviceApi apiInstance = new DeviceApi();
try {
    InfoResponse result = apiInstance.infoJsonGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DeviceApi#infoJsonGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InfoResponse**](InfoResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="pingJsonGet"></a>
# **pingJsonGet**
> PingResponse pingJsonGet()

Simple function to detect the presence of the device on the network. Typically, only used for system setup validation and testing.

### Example
```java
// Import classes:
//import com.citypay.invoker.ApiException;
//import com.citypay.pos.api.kinetic.DeviceApi;


DeviceApi apiInstance = new DeviceApi();
try {
    PingResponse result = apiInstance.pingJsonGet();
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling DeviceApi#pingJsonGet");
    e.printStackTrace();
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**PingResponse**](PingResponse.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

