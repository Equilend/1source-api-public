# RecallsApi

All URIs are relative to *https://stageapi.equilend.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**ledgerLoansLoanIdRecallsGet**](RecallsApi.md#ledgerLoansLoanIdRecallsGet) | **GET** /ledger/loans/{loanId}/recalls | Read collection of recalls against loan specified by &#x27;loanId&#x27;
[**ledgerLoansLoanIdRecallsPost**](RecallsApi.md#ledgerLoansLoanIdRecallsPost) | **POST** /ledger/loans/{loanId}/recalls | Initiates a recall
[**ledgerLoansLoanIdRecallsRecallIdAcknowledgePost**](RecallsApi.md#ledgerLoansLoanIdRecallsRecallIdAcknowledgePost) | **POST** /ledger/loans/{loanId}/recalls/{recallId}/acknowledge | Acknowledge a pending recall. No change to status.
[**ledgerLoansLoanIdRecallsRecallIdCancelPost**](RecallsApi.md#ledgerLoansLoanIdRecallsRecallIdCancelPost) | **POST** /ledger/loans/{loanId}/recalls/{recallId}/cancel | Cancel a recall in \&quot;proposed\&quot; state. Original proposer only.
[**ledgerLoansLoanIdRecallsRecallIdGet**](RecallsApi.md#ledgerLoansLoanIdRecallsRecallIdGet) | **GET** /ledger/loans/{loanId}/recalls/{recallId} | Read a recall
[**ledgerLoansLoanIdRecallsRecallIdPatch**](RecallsApi.md#ledgerLoansLoanIdRecallsRecallIdPatch) | **PATCH** /ledger/loans/{loanId}/recalls/{recallId} | Update unilateral fields on a recall
[**ledgerRecallsGet**](RecallsApi.md#ledgerRecallsGet) | **GET** /ledger/recalls | Read collection of recalls
[**ledgerRecallsRecallIdGet**](RecallsApi.md#ledgerRecallsRecallIdGet) | **GET** /ledger/recalls/{recallId} | Read a recall

<a name="ledgerLoansLoanIdRecallsGet"></a>
# **ledgerLoansLoanIdRecallsGet**
> Recalls ledgerLoansLoanIdRecallsGet(loanId)

Read collection of recalls against loan specified by &#x27;loanId&#x27;

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.RecallsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

RecallsApi apiInstance = new RecallsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
try {
    Recalls result = apiInstance.ledgerLoansLoanIdRecallsGet(loanId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RecallsApi#ledgerLoansLoanIdRecallsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |

### Return type

[**Recalls**](Recalls.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdRecallsPost"></a>
# **ledgerLoansLoanIdRecallsPost**
> ledgerLoansLoanIdRecallsPost(body, loanId)

Initiates a recall

Initiates a recall notice to return securities on a loan.  ### Validation rules:   - Caller must be the lender on the loan   - &#x60;loanId&#x60; -- must reference an &#x60;OPEN&#x60; loan   - &#x60;executionVenue&#x60;     - is mandatory     - if &#x60;executionVenue.type&#x60; &#x3D;&#x3D; &#x60;ONPLATFORM&#x60; then all other elements in &#x60;executionVenue&#x60; must be null     - if &#x60;executionVenue.type&#x60; &#x3D;&#x3D; &#x60;OFFPLATFORM&#x60; then &#x60;executionVenue.partyId&#x60;, &#x60;executionVenue.venueName&#x60;, and &#x60;executionVenue.partyRole&#x60; are mandatory   - &#x60;quantity&#x60;     - is mandatory     - must be &lt; 0     - must be &lt;&#x3D; loan open quantity - all existing open recall quantities   - &#x60;recallDate&#x60;     - is mandatory     - must be &gt;&#x3D; current day   - &#x60;recallDueDate&#x60;     - is mandatory     - must be &gt; &#x60;recallDate&#x60; 

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.RecallsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

RecallsApi apiInstance = new RecallsApi();
RecallProposal body = new RecallProposal(); // RecallProposal | New recall proposed against loan
String loanId = "loanId_example"; // String | The unique identifier of a loan
try {
    apiInstance.ledgerLoansLoanIdRecallsPost(body, loanId);
} catch (ApiException e) {
    System.err.println("Exception when calling RecallsApi#ledgerLoansLoanIdRecallsPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RecallProposal**](RecallProposal.md)| New recall proposed against loan |
 **loanId** | [**String**](.md)| The unique identifier of a loan |

### Return type

null (empty response body)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ledgerLoansLoanIdRecallsRecallIdAcknowledgePost"></a>
# **ledgerLoansLoanIdRecallsRecallIdAcknowledgePost**
> LedgerResponse ledgerLoansLoanIdRecallsRecallIdAcknowledgePost(body, loanId, recallId)

Acknowledge a pending recall. No change to status.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.RecallsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

RecallsApi apiInstance = new RecallsApi();
RecallAcknowledgement body = new RecallAcknowledgement(); // RecallAcknowledgement | Acknowledge return with positive/negative intent
String loanId = "loanId_example"; // String | The unique identifier of a loan
String recallId = "recallId_example"; // String | The unique identifier of a recall
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdRecallsRecallIdAcknowledgePost(body, loanId, recallId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RecallsApi#ledgerLoansLoanIdRecallsRecallIdAcknowledgePost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**RecallAcknowledgement**](RecallAcknowledgement.md)| Acknowledge return with positive/negative intent |
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **recallId** | [**String**](.md)| The unique identifier of a recall |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ledgerLoansLoanIdRecallsRecallIdCancelPost"></a>
# **ledgerLoansLoanIdRecallsRecallIdCancelPost**
> LedgerResponse ledgerLoansLoanIdRecallsRecallIdCancelPost(loanId, recallId)

Cancel a recall in \&quot;proposed\&quot; state. Original proposer only.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.RecallsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

RecallsApi apiInstance = new RecallsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String recallId = "recallId_example"; // String | The unique identifier of a recall
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdRecallsRecallIdCancelPost(loanId, recallId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RecallsApi#ledgerLoansLoanIdRecallsRecallIdCancelPost");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **recallId** | [**String**](.md)| The unique identifier of a recall |

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdRecallsRecallIdGet"></a>
# **ledgerLoansLoanIdRecallsRecallIdGet**
> Recall ledgerLoansLoanIdRecallsRecallIdGet(loanId, recallId)

Read a recall

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.RecallsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

RecallsApi apiInstance = new RecallsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String recallId = "recallId_example"; // String | The unique identifier of a recall
try {
    Recall result = apiInstance.ledgerLoansLoanIdRecallsRecallIdGet(loanId, recallId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RecallsApi#ledgerLoansLoanIdRecallsRecallIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **recallId** | [**String**](.md)| The unique identifier of a recall |

### Return type

[**Recall**](Recall.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdRecallsRecallIdPatch"></a>
# **ledgerLoansLoanIdRecallsRecallIdPatch**
> LedgerResponse ledgerLoansLoanIdRecallsRecallIdPatch(loanId, recallId, body)

Update unilateral fields on a recall

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.RecallsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

RecallsApi apiInstance = new RecallsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
String recallId = "recallId_example"; // String | The unique identifier of a recall
RecallsRecallIdBody body = new RecallsRecallIdBody(); // RecallsRecallIdBody | 
try {
    LedgerResponse result = apiInstance.ledgerLoansLoanIdRecallsRecallIdPatch(loanId, recallId, body);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RecallsApi#ledgerLoansLoanIdRecallsRecallIdPatch");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **recallId** | [**String**](.md)| The unique identifier of a recall |
 **body** | [**RecallsRecallIdBody**](RecallsRecallIdBody.md)|  | [optional]

### Return type

[**LedgerResponse**](LedgerResponse.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="ledgerRecallsGet"></a>
# **ledgerRecallsGet**
> Recalls ledgerRecallsGet(since, before, size, recallStatus)

Read collection of recalls

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.RecallsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

RecallsApi apiInstance = new RecallsApi();
OffsetDateTime since = new OffsetDateTime(); // OffsetDateTime | Recalls updated (since) timestamp UTC
OffsetDateTime before = new OffsetDateTime(); // OffsetDateTime | Recalls updated (before) timestamp UTC
Integer size = 56; // Integer | Number of recalls to be returned. Can be used to facilitate paging
RecallStatus recallStatus = new RecallStatus(); // RecallStatus | Recalls matching status RECALL STATUS
try {
    Recalls result = apiInstance.ledgerRecallsGet(since, before, size, recallStatus);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RecallsApi#ledgerRecallsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **since** | [**OffsetDateTime**](.md)| Recalls updated (since) timestamp UTC | [optional]
 **before** | [**OffsetDateTime**](.md)| Recalls updated (before) timestamp UTC | [optional]
 **size** | **Integer**| Number of recalls to be returned. Can be used to facilitate paging | [optional]
 **recallStatus** | [**RecallStatus**](.md)| Recalls matching status RECALL STATUS | [optional]

### Return type

[**Recalls**](Recalls.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerRecallsRecallIdGet"></a>
# **ledgerRecallsRecallIdGet**
> Recall ledgerRecallsRecallIdGet(recallId)

Read a recall

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.RecallsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

RecallsApi apiInstance = new RecallsApi();
String recallId = "recallId_example"; // String | The unique identifier of a recall
try {
    Recall result = apiInstance.ledgerRecallsRecallIdGet(recallId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling RecallsApi#ledgerRecallsRecallIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **recallId** | [**String**](.md)| The unique identifier of a recall |

### Return type

[**Recall**](Recall.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

