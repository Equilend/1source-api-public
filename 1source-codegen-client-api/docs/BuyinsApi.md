# BuyinsApi

All URIs are relative to *https://stageapi.equilend.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**ledgerBuyinsCompletesBuyinCompleteIdGet**](BuyinsApi.md#ledgerBuyinsCompletesBuyinCompleteIdGet) | **GET** /ledger/buyins/completes/{buyinCompleteId} | Read a completed buyin
[**ledgerBuyinsCompletesGet**](BuyinsApi.md#ledgerBuyinsCompletesGet) | **GET** /ledger/buyins/completes | Read collection of completed buyins
[**ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdAcceptPost**](BuyinsApi.md#ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdAcceptPost) | **POST** /ledger/loans/{loanId}/buyins/completes/{buyinCompleteId}/accept | Complete some or all of an initiated Buyin
[**ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdGet**](BuyinsApi.md#ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdGet) | **GET** /ledger/loans/{loanId}/buyins/completes/{buyinCompleteId} | Read a completed Buyin
[**ledgerLoansLoanIdBuyinsCompletesGet**](BuyinsApi.md#ledgerLoansLoanIdBuyinsCompletesGet) | **GET** /ledger/loans/{loanId}/buyins/completes | Read collection of completed buyins against loan specified by &#x27;loanId&#x27;
[**ledgerLoansLoanIdBuyinsCompletesPost**](BuyinsApi.md#ledgerLoansLoanIdBuyinsCompletesPost) | **POST** /ledger/loans/{loanId}/buyins/completes | Complete a buyin

<a name="ledgerBuyinsCompletesBuyinCompleteIdGet"></a>
# **ledgerBuyinsCompletesBuyinCompleteIdGet**
> BuyinComplete ledgerBuyinsCompletesBuyinCompleteIdGet(buyinCompleteId)

Read a completed buyin

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.BuyinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

BuyinsApi apiInstance = new BuyinsApi();
String buyinCompleteId = "buyinCompleteId_example"; // String | The unique identifier of a completed buyin
try {
    BuyinComplete result = apiInstance.ledgerBuyinsCompletesBuyinCompleteIdGet(buyinCompleteId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BuyinsApi#ledgerBuyinsCompletesBuyinCompleteIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **buyinCompleteId** | [**String**](.md)| The unique identifier of a completed buyin |

### Return type

[**BuyinComplete**](BuyinComplete.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerBuyinsCompletesGet"></a>
# **ledgerBuyinsCompletesGet**
> BuyinCompletes ledgerBuyinsCompletesGet(since, before, size, buyinCompleteStatus)

Read collection of completed buyins

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.BuyinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

BuyinsApi apiInstance = new BuyinsApi();
OffsetDateTime since = new OffsetDateTime(); // OffsetDateTime | Completed buyins updated (since) timestamp UTC
OffsetDateTime before = new OffsetDateTime(); // OffsetDateTime | Completed buyins updated (before) timestamp UTC
Integer size = 56; // Integer | Number of completed buyins to be returned. Can be used to facilitate paging
BuyinCompleteStatus buyinCompleteStatus = new BuyinCompleteStatus(); // BuyinCompleteStatus | Completed buyins matching status BUYIN COMPLETE STATUS
try {
    BuyinCompletes result = apiInstance.ledgerBuyinsCompletesGet(since, before, size, buyinCompleteStatus);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BuyinsApi#ledgerBuyinsCompletesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **since** | [**OffsetDateTime**](.md)| Completed buyins updated (since) timestamp UTC | [optional]
 **before** | [**OffsetDateTime**](.md)| Completed buyins updated (before) timestamp UTC | [optional]
 **size** | **Integer**| Number of completed buyins to be returned. Can be used to facilitate paging | [optional]
 **buyinCompleteStatus** | [**BuyinCompleteStatus**](.md)| Completed buyins matching status BUYIN COMPLETE STATUS | [optional]

### Return type

[**BuyinCompletes**](BuyinCompletes.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdAcceptPost"></a>
# **ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdAcceptPost**
> LedgerResponse ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdAcceptPost(loanId, buyinCompleteId)

Complete some or all of an initiated Buyin

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.BuyinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

BuyinsApi apiInstance = new BuyinsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String buyinCompleteId = "buyinCompleteId_example"; // String | The unique identifier of a completed buyin
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdAcceptPost(loanId, buyinCompleteId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BuyinsApi#ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdAcceptPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **buyinCompleteId** | [**String**](.md)| The unique identifier of a completed buyin |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdGet"></a>
# **ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdGet**
> BuyinComplete ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdGet(loanId, buyinCompleteId)

Read a completed Buyin

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.BuyinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

BuyinsApi apiInstance = new BuyinsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String buyinCompleteId = "buyinCompleteId_example"; // String | The unique identifier of a completed buyin
try {
    BuyinComplete result = apiInstance.ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdGet(loanId, buyinCompleteId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BuyinsApi#ledgerLoansLoanIdBuyinsCompletesBuyinCompleteIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **buyinCompleteId** | [**String**](.md)| The unique identifier of a completed buyin |

### Return type

[**BuyinComplete**](BuyinComplete.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdBuyinsCompletesGet"></a>
# **ledgerLoansLoanIdBuyinsCompletesGet**
> BuyinCompletes ledgerLoansLoanIdBuyinsCompletesGet(loanId)

Read collection of completed buyins against loan specified by &#x27;loanId&#x27;

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.BuyinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

BuyinsApi apiInstance = new BuyinsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
try {
    BuyinCompletes result = apiInstance.ledgerLoansLoanIdBuyinsCompletesGet(loanId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling BuyinsApi#ledgerLoansLoanIdBuyinsCompletesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |

### Return type

[**BuyinCompletes**](BuyinCompletes.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdBuyinsCompletesPost"></a>
# **ledgerLoansLoanIdBuyinsCompletesPost**
> ledgerLoansLoanIdBuyinsCompletesPost(loanId, body)

Complete a buyin

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.BuyinsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

BuyinsApi apiInstance = new BuyinsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
BuyinCompleteRequest body = new BuyinCompleteRequest(); // BuyinCompleteRequest | 
try {
    apiInstance.ledgerLoansLoanIdBuyinsCompletesPost(loanId, body);
} catch (ApiException e) {
    System.err.println("Exception when calling BuyinsApi#ledgerLoansLoanIdBuyinsCompletesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **body** | [**BuyinCompleteRequest**](BuyinCompleteRequest.md)|  | [optional]

### Return type

null (empty response body)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

