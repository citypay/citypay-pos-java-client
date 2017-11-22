# swagger-java-client

## Requirements

Building the API client library requires [Maven](https://maven.apache.org/) to be installed.

## Installation

To install the API client library to your local Maven repository, simply execute:

```shell
mvn install
```

To deploy it to a remote Maven repository instead, configure the settings of the repository and execute:

```shell
mvn deploy
```

Refer to the [official documentation](https://maven.apache.org/plugins/maven-deploy-plugin/usage.html) for more information.

### Maven users

Add this dependency to your project's POM:

```xml
<dependency>
    <groupId>io.swagger</groupId>
    <artifactId>swagger-java-client</artifactId>
    <version>1.0.0</version>
    <scope>compile</scope>
</dependency>
```

### Gradle users

Add this dependency to your project's build file:

```groovy
compile "io.swagger:swagger-java-client:1.0.0"
```

### Others

At first generate the JAR by executing:

    mvn package

Then manually install the following JARs:

* target/swagger-java-client-1.0.0.jar
* target/lib/*.jar

## Getting Started

Please follow the [installation](#installation) instruction and execute the following Java code:

```java

import io.swagger.client.*;
import io.swagger.client.auth.*;
import io.swagger.client.model.*;
import io.swagger.client.api.DeviceModuleApi;

import java.io.File;
import java.util.*;

public class DeviceModuleApiExample {

    public static void main(String[] args) {
        
        DeviceModuleApi apiInstance = new DeviceModuleApi();
        String deviceId = "deviceId_example"; // String | The name of the target device used by the API.
        try {
            DeviceInfo result = apiInstance.deviceInfo(deviceId);
            System.out.println(result);
        } catch (ApiException e) {
            System.err.println("Exception when calling DeviceModuleApi#deviceInfo");
            e.printStackTrace();
        }
    }
}

```

## Documentation for API Endpoints

All URIs are relative to *https://pos.citypay.com/citypay-pos-api/1.0.0*

Class | Method | HTTP request | Description
------------ | ------------- | ------------- | -------------
*DeviceModuleApi* | [**deviceInfo**](docs/DeviceModuleApi.md#deviceInfo) | **GET** /device/{deviceId}/info | Device Information
*DeviceModuleApi* | [**ping**](docs/DeviceModuleApi.md#ping) | **GET** /device/{deviceId}/ping | Device Ping
*PaymentModuleApi* | [**receipt**](docs/PaymentModuleApi.md#receipt) | **POST** /receipt | Receipt Print
*PaymentModuleApi* | [**refund**](docs/PaymentModuleApi.md#refund) | **POST** /refund | Refund Transaction
*PaymentModuleApi* | [**reversal**](docs/PaymentModuleApi.md#reversal) | **POST** /reversal | Reversal Tranasction
*PaymentModuleApi* | [**sale**](docs/PaymentModuleApi.md#sale) | **POST** /sale | Sale Transaction
*PaymentModuleApi* | [**transaction**](docs/PaymentModuleApi.md#transaction) | **POST** /transaction | Transaction Status


## Documentation for Models

 - [DeviceInfo](docs/DeviceInfo.md)
 - [PrintRequest](docs/PrintRequest.md)
 - [Receipt](docs/Receipt.md)
 - [Result](docs/Result.md)
 - [ReversalRequest](docs/ReversalRequest.md)
 - [SaleRequest](docs/SaleRequest.md)
 - [SaleResponse](docs/SaleResponse.md)
 - [SuccessResponse](docs/SuccessResponse.md)
 - [TransactionData](docs/TransactionData.md)
 - [TransactionProgress](docs/TransactionProgress.md)
 - [TransactionResult](docs/TransactionResult.md)


## Documentation for Authorization

All endpoints do not require authorization.
Authentication schemes defined for the API:

## Recommendation

It's recommended to create an instance of `ApiClient` per thread in a multithreaded environment to avoid any potential issues.

## Author

dev@citypay.com

