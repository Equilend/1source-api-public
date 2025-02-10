# ReturnsApi

All URIs are relative to *https://stageapi.equilend.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**ledgerLoansLoanIdReturnsGet**](ReturnsApi.md#ledgerLoansLoanIdReturnsGet) | **GET** /ledger/loans/{loanId}/returns | Read collection of returns against loan specified by &#x27;loanId&#x27;
[**ledgerLoansLoanIdReturnsPost**](ReturnsApi.md#ledgerLoansLoanIdReturnsPost) | **POST** /ledger/loans/{loanId}/returns | Create a return
[**ledgerLoansLoanIdReturnsReturnIdAcknowledgePost**](ReturnsApi.md#ledgerLoansLoanIdReturnsReturnIdAcknowledgePost) | **POST** /ledger/loans/{loanId}/returns/{returnId}/acknowledge | Acknowledge a pending return. No change to status.
[**ledgerLoansLoanIdReturnsReturnIdCancelPost**](ReturnsApi.md#ledgerLoansLoanIdReturnsReturnIdCancelPost) | **POST** /ledger/loans/{loanId}/returns/{returnId}/cancel | Cancel a return in \&quot;proposed\&quot; or \&quot;pending\&quot; state. Original proposer only.
[**ledgerLoansLoanIdReturnsReturnIdGet**](ReturnsApi.md#ledgerLoansLoanIdReturnsReturnIdGet) | **GET** /ledger/loans/{loanId}/returns/{returnId} | Read a return
[**ledgerLoansLoanIdReturnsReturnIdPatch**](ReturnsApi.md#ledgerLoansLoanIdReturnsReturnIdPatch) | **PATCH** /ledger/loans/{loanId}/returns/{returnId} | Update unilateral fields on a return
[**ledgerReturnsGet**](ReturnsApi.md#ledgerReturnsGet) | **GET** /ledger/returns | Read collection of returns
[**ledgerReturnsReturnIdGet**](ReturnsApi.md#ledgerReturnsReturnIdGet) | **GET** /ledger/returns/{returnId} | Read a return

<a name="ledgerLoansLoanIdReturnsGet"></a>
# **ledgerLoansLoanIdReturnsGet**
> Returns ledgerLoansLoanIdReturnsGet(loanId)

Read collection of returns against loan specified by &#x27;loanId&#x27;

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReturnsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReturnsApi apiInstance = new ReturnsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
try {
    Returns result = apiInstance.ledgerLoansLoanIdReturnsGet(loanId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReturnsApi#ledgerLoansLoanIdReturnsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |

### Return type

[**Returns**](Returns.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReturnsPost"></a>
# **ledgerLoansLoanIdReturnsPost**
> ledgerLoansLoanIdReturnsPost(body, loanId)

Create a return

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReturnsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReturnsApi apiInstance = new ReturnsApi();
ReturnProposal body = new ReturnProposal(); // ReturnProposal | New return proposed against loan
String loanId = "loanId_example"; // String | The unique identifier of a loan
try {
    apiInstance.ledgerLoansLoanIdReturnsPost(body, loanId);
} catch (ApiException e) {
    System.err.println("Exception when calling ReturnsApi#ledgerLoansLoanIdReturnsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ReturnProposal**](ReturnProposal.md)| New return proposed against loan |
 **loanId** | [**String**](.md)| The unique identifier of a loan |

### Return type

null (empty response body)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReturnsReturnIdAcknowledgePost"></a>
# **ledgerLoansLoanIdReturnsReturnIdAcknowledgePost**
> LedgerResponse ledgerLoansLoanIdReturnsReturnIdAcknowledgePost(body, loanId, returnId)

Acknowledge a pending return. No change to status.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReturnsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReturnsApi apiInstance = new ReturnsApi();
ReturnAcknowledgement body = new ReturnAcknowledgement(); // ReturnAcknowledgement | Acknowledge return with positive/negative intent
String loanId = "loanId_example"; // String | The unique identifier of a loan
String returnId = "returnId_example"; // String | The unique identifier of a return
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdReturnsReturnIdAcknowledgePost(body, loanId, returnId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReturnsApi#ledgerLoansLoanIdReturnsReturnIdAcknowledgePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**ReturnAcknowledgement**](ReturnAcknowledgement.md)| Acknowledge return with positive/negative intent |
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **returnId** | [**String**](.md)| The unique identifier of a return |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReturnsReturnIdCancelPost"></a>
# **ledgerLoansLoanIdReturnsReturnIdCancelPost**
> LedgerResponse ledgerLoansLoanIdReturnsReturnIdCancelPost(loanId, returnId)

Cancel a return in \&quot;proposed\&quot; or \&quot;pending\&quot; state. Original proposer only.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReturnsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReturnsApi apiInstance = new ReturnsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String returnId = "returnId_example"; // String | The unique identifier of a return
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdReturnsReturnIdCancelPost(loanId, returnId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReturnsApi#ledgerLoansLoanIdReturnsReturnIdCancelPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **returnId** | [**String**](.md)| The unique identifier of a return |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReturnsReturnIdGet"></a>
# **ledgerLoansLoanIdReturnsReturnIdGet**
> ModelReturn ledgerLoansLoanIdReturnsReturnIdGet(loanId, returnId)

Read a return

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReturnsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReturnsApi apiInstance = new ReturnsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String returnId = "returnId_example"; // String | The unique identifier of a return
try {
    ModelReturn result = apiInstance.ledgerLoansLoanIdReturnsReturnIdGet(loanId, returnId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReturnsApi#ledgerLoansLoanIdReturnsReturnIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **returnId** | [**String**](.md)| The unique identifier of a return |

### Return type

[**ModelReturn**](ModelReturn.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReturnsReturnIdPatch"></a>
# **ledgerLoansLoanIdReturnsReturnIdPatch**
> LedgerResponse ledgerLoansLoanIdReturnsReturnIdPatch(loanId, returnId, body)

Update unilateral fields on a return

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReturnsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReturnsApi apiInstance = new ReturnsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String returnId = "returnId_example"; // String | The unique identifier of a return
ReturnsReturnIdBody body = new ReturnsReturnIdBody(); // ReturnsReturnIdBody | 
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdReturnsReturnIdPatch(loanId, returnId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReturnsApi#ledgerLoansLoanIdReturnsReturnIdPatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **returnId** | [**String**](.md)| The unique identifier of a return |
 **body** | [**ReturnsReturnIdBody**](ReturnsReturnIdBody.md)|  | [optional]

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ledgerReturnsGet"></a>
# **ledgerReturnsGet**
> Returns ledgerReturnsGet(since, before, size, returnStatus)

Read collection of returns

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReturnsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReturnsApi apiInstance = new ReturnsApi();
OffsetDateTime since = new OffsetDateTime(); // OffsetDateTime | Returns updated (since) timestamp UTC
OffsetDateTime before = new OffsetDateTime(); // OffsetDateTime | Returns updated (before) timestamp UTC
Integer size = 56; // Integer | Number of returns to be returned. Can be used to facilitate paging
ReturnStatus returnStatus = new ReturnStatus(); // ReturnStatus | Returns matching status RETURN STATUS
try {
    Returns result = apiInstance.ledgerReturnsGet(since, before, size, returnStatus);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReturnsApi#ledgerReturnsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **since** | [**OffsetDateTime**](.md)| Returns updated (since) timestamp UTC | [optional]
 **before** | [**OffsetDateTime**](.md)| Returns updated (before) timestamp UTC | [optional]
 **size** | **Integer**| Number of returns to be returned. Can be used to facilitate paging | [optional]
 **returnStatus** | [**ReturnStatus**](.md)| Returns matching status RETURN STATUS | [optional]

### Return type

[**Returns**](Returns.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerReturnsReturnIdGet"></a>
# **ledgerReturnsReturnIdGet**
> ModelReturn ledgerReturnsReturnIdGet(returnId)

Read a return

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReturnsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReturnsApi apiInstance = new ReturnsApi();
String returnId = "returnId_example"; // String | The unique identifier of a return
try {
    ModelReturn result = apiInstance.ledgerReturnsReturnIdGet(returnId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReturnsApi#ledgerReturnsReturnIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **returnId** | [**String**](.md)| The unique identifier of a return |

### Return type

[**ModelReturn**](ModelReturn.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

