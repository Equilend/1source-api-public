# ReratesApi

All URIs are relative to *https://stageapi.equilend.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**ledgerLoansLoanIdReratesGet**](ReratesApi.md#ledgerLoansLoanIdReratesGet) | **GET** /ledger/loans/{loanId}/rerates | Read collection of rerates against loan specified by &#x27;loanId&#x27;
[**ledgerLoansLoanIdReratesPost**](ReratesApi.md#ledgerLoansLoanIdReratesPost) | **POST** /ledger/loans/{loanId}/rerates | Create a rerate.
[**ledgerLoansLoanIdReratesRerateIdApprovePost**](ReratesApi.md#ledgerLoansLoanIdReratesRerateIdApprovePost) | **POST** /ledger/loans/{loanId}/rerates/{rerateId}/approve | Approve a rerate in \&quot;proposed\&quot; state.
[**ledgerLoansLoanIdReratesRerateIdCancelPost**](ReratesApi.md#ledgerLoansLoanIdReratesRerateIdCancelPost) | **POST** /ledger/loans/{loanId}/rerates/{rerateId}/cancel | Cancel a rerate in \&quot;proposed\&quot; state. Original proposer only.
[**ledgerLoansLoanIdReratesRerateIdCancelpendingPost**](ReratesApi.md#ledgerLoansLoanIdReratesRerateIdCancelpendingPost) | **POST** /ledger/loans/{loanId}/rerates/{rerateId}/cancelpending | Cancel a rerate in \&quot;pending\&quot; state. Either party can initiate. If canceled by a delegated Venue, the cancel will be done unilaterally
[**ledgerLoansLoanIdReratesRerateIdDeclinePost**](ReratesApi.md#ledgerLoansLoanIdReratesRerateIdDeclinePost) | **POST** /ledger/loans/{loanId}/rerates/{rerateId}/decline | Decline a rerate in \&quot;proposed\&quot; state.
[**ledgerLoansLoanIdReratesRerateIdGet**](ReratesApi.md#ledgerLoansLoanIdReratesRerateIdGet) | **GET** /ledger/loans/{loanId}/rerates/{rerateId} | Read a rerate
[**ledgerReratesGet**](ReratesApi.md#ledgerReratesGet) | **GET** /ledger/rerates | Read collection of rerates
[**ledgerReratesRerateIdGet**](ReratesApi.md#ledgerReratesRerateIdGet) | **GET** /ledger/rerates/{rerateId} | Read a rerate

<a name="ledgerLoansLoanIdReratesGet"></a>
# **ledgerLoansLoanIdReratesGet**
> Rerates ledgerLoansLoanIdReratesGet(loanId)

Read collection of rerates against loan specified by &#x27;loanId&#x27;

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
try {
    Rerates result = apiInstance.ledgerLoansLoanIdReratesGet(loanId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerLoansLoanIdReratesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |

### Return type

[**Rerates**](Rerates.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReratesPost"></a>
# **ledgerLoansLoanIdReratesPost**
> ledgerLoansLoanIdReratesPost(body, loanId)

Create a rerate.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
RerateProposal body = new RerateProposal(); // RerateProposal | New rerate proposed against loan
String loanId = "loanId_example"; // String | The unique identifier of a loan
try {
    apiInstance.ledgerLoansLoanIdReratesPost(body, loanId);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerLoansLoanIdReratesPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RerateProposal**](RerateProposal.md)| New rerate proposed against loan |
 **loanId** | [**String**](.md)| The unique identifier of a loan |

### Return type

null (empty response body)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReratesRerateIdApprovePost"></a>
# **ledgerLoansLoanIdReratesRerateIdApprovePost**
> LedgerResponse ledgerLoansLoanIdReratesRerateIdApprovePost(loanId, rerateId)

Approve a rerate in \&quot;proposed\&quot; state.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String rerateId = "rerateId_example"; // String | The unique identifier of a rerate
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdReratesRerateIdApprovePost(loanId, rerateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerLoansLoanIdReratesRerateIdApprovePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **rerateId** | [**String**](.md)| The unique identifier of a rerate |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReratesRerateIdCancelPost"></a>
# **ledgerLoansLoanIdReratesRerateIdCancelPost**
> LedgerResponse ledgerLoansLoanIdReratesRerateIdCancelPost(loanId, rerateId)

Cancel a rerate in \&quot;proposed\&quot; state. Original proposer only.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String rerateId = "rerateId_example"; // String | The unique identifier of a rerate
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdReratesRerateIdCancelPost(loanId, rerateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerLoansLoanIdReratesRerateIdCancelPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **rerateId** | [**String**](.md)| The unique identifier of a rerate |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReratesRerateIdCancelpendingPost"></a>
# **ledgerLoansLoanIdReratesRerateIdCancelpendingPost**
> LedgerResponse ledgerLoansLoanIdReratesRerateIdCancelpendingPost(loanId, rerateId)

Cancel a rerate in \&quot;pending\&quot; state. Either party can initiate. If canceled by a delegated Venue, the cancel will be done unilaterally

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String rerateId = "rerateId_example"; // String | The unique identifier of a rerate
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdReratesRerateIdCancelpendingPost(loanId, rerateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerLoansLoanIdReratesRerateIdCancelpendingPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **rerateId** | [**String**](.md)| The unique identifier of a rerate |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReratesRerateIdDeclinePost"></a>
# **ledgerLoansLoanIdReratesRerateIdDeclinePost**
> LedgerResponse ledgerLoansLoanIdReratesRerateIdDeclinePost(body, loanId, rerateId)

Decline a rerate in \&quot;proposed\&quot; state.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
RerateDeclineErrorResponse body = new RerateDeclineErrorResponse(); // RerateDeclineErrorResponse | Provide reason and details for declining rerate proposal
String loanId = "loanId_example"; // String | The unique identifier of a loan
String rerateId = "rerateId_example"; // String | The unique identifier of a rerate
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdReratesRerateIdDeclinePost(body, loanId, rerateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerLoansLoanIdReratesRerateIdDeclinePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RerateDeclineErrorResponse**](RerateDeclineErrorResponse.md)| Provide reason and details for declining rerate proposal |
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **rerateId** | [**String**](.md)| The unique identifier of a rerate |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ledgerLoansLoanIdReratesRerateIdGet"></a>
# **ledgerLoansLoanIdReratesRerateIdGet**
> Rerate ledgerLoansLoanIdReratesRerateIdGet(loanId, rerateId)

Read a rerate

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String rerateId = "rerateId_example"; // String | The unique identifier of a rerate
try {
    Rerate result = apiInstance.ledgerLoansLoanIdReratesRerateIdGet(loanId, rerateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerLoansLoanIdReratesRerateIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **rerateId** | [**String**](.md)| The unique identifier of a rerate |

### Return type

[**Rerate**](Rerate.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerReratesGet"></a>
# **ledgerReratesGet**
> Rerates ledgerReratesGet(since, before, size, rerateStatus)

Read collection of rerates

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
OffsetDateTime since = new OffsetDateTime(); // OffsetDateTime | Rerates updated (since) timestamp UTC
OffsetDateTime before = new OffsetDateTime(); // OffsetDateTime | Rerates updated (before) timestamp UTC
Integer size = 56; // Integer | Number of rerates to be returned. Can be used to facilitate paging
RerateStatus rerateStatus = new RerateStatus(); // RerateStatus | Loans matching status RERATE STATUS
try {
    Rerates result = apiInstance.ledgerReratesGet(since, before, size, rerateStatus);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerReratesGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **since** | [**OffsetDateTime**](.md)| Rerates updated (since) timestamp UTC | [optional]
 **before** | [**OffsetDateTime**](.md)| Rerates updated (before) timestamp UTC | [optional]
 **size** | **Integer**| Number of rerates to be returned. Can be used to facilitate paging | [optional]
 **rerateStatus** | [**RerateStatus**](.md)| Loans matching status RERATE STATUS | [optional]

### Return type

[**Rerates**](Rerates.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerReratesRerateIdGet"></a>
# **ledgerReratesRerateIdGet**
> Rerate ledgerReratesRerateIdGet(rerateId)

Read a rerate

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.ReratesApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

ReratesApi apiInstance = new ReratesApi();
String rerateId = "rerateId_example"; // String | The unique identifier of a rerate
try {
    Rerate result = apiInstance.ledgerReratesRerateIdGet(rerateId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling ReratesApi#ledgerReratesRerateIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **rerateId** | [**String**](.md)| The unique identifier of a rerate |

### Return type

[**Rerate**](Rerate.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

