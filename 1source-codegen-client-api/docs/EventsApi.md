# EventsApi

All URIs are relative to *https://stageapi.equilend.com/v1*

Method | HTTP request | Description
------------- | ------------- | -------------
[**ledgerEventsEventIdGet**](EventsApi.md#ledgerEventsEventIdGet) | **GET** /ledger/events/{eventId} | Read an event
[**ledgerEventsGet**](EventsApi.md#ledgerEventsGet) | **GET** /ledger/events | Read collection of events. With no parameters returns events since start of current day.
[**ledgerLoansLoanIdEventsEventIdGet**](EventsApi.md#ledgerLoansLoanIdEventsEventIdGet) | **GET** /ledger/loans/{loanId}/events/{eventId} | Read an event
[**ledgerLoansLoanIdEventsGet**](EventsApi.md#ledgerLoansLoanIdEventsGet) | **GET** /ledger/loans/{loanId}/events | Read collection of events against a specific loan. With no parameters returns events since start of current day.

<a name="ledgerEventsEventIdGet"></a>
# **ledgerEventsEventIdGet**
> Event ledgerEventsEventIdGet(eventId)

Read an event

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.EventsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

EventsApi apiInstance = new EventsApi();
Long eventId = 56L; // Long | The unique identifier of an event
try {
    Event result = apiInstance.ledgerEventsEventIdGet(eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsApi#ledgerEventsEventIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **eventId** | [**Long**](.md)| The unique identifier of an event |

### Return type

[**Event**](Event.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerEventsGet"></a>
# **ledgerEventsGet**
> Events ledgerEventsGet(eventType, beforeEventId, fromEventId, since, before, size, sortOrder)

Read collection of events. With no parameters returns events since start of current day.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.EventsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

EventsApi apiInstance = new EventsApi();
List<EventType> eventType = Arrays.asList(new EventType()); // List<EventType> | Filter by event type
Long beforeEventId = 789L; // Long | 
Long fromEventId = 789L; // Long | 
OffsetDateTime since = new OffsetDateTime(); // OffsetDateTime | Events (since) timestamp UTC
OffsetDateTime before = new OffsetDateTime(); // OffsetDateTime | Events (before) timestamp UTC
Integer size = 56; // Integer | Number of events to be returned. Can be used to facilitate paging. Defaults to 100
SortOrder sortOrder = new SortOrder(); // SortOrder | Sort events by order of creation ascending or descending. Defaults to ASCENDING
try {
    Events result = apiInstance.ledgerEventsGet(eventType, beforeEventId, fromEventId, since, before, size, sortOrder);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsApi#ledgerEventsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **eventType** | [**List&lt;EventType&gt;**](EventType.md)| Filter by event type | [optional]
 **beforeEventId** | **Long**|  | [optional]
 **fromEventId** | **Long**|  | [optional]
 **since** | [**OffsetDateTime**](.md)| Events (since) timestamp UTC | [optional]
 **before** | [**OffsetDateTime**](.md)| Events (before) timestamp UTC | [optional]
 **size** | **Integer**| Number of events to be returned. Can be used to facilitate paging. Defaults to 100 | [optional]
 **sortOrder** | [**SortOrder**](.md)| Sort events by order of creation ascending or descending. Defaults to ASCENDING | [optional]

### Return type

[**Events**](Events.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdEventsEventIdGet"></a>
# **ledgerLoansLoanIdEventsEventIdGet**
> Event ledgerLoansLoanIdEventsEventIdGet(loanId, eventId)

Read an event

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.EventsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

EventsApi apiInstance = new EventsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
Long eventId = 56L; // Long | The unique identifier of an Event
try {
    Event result = apiInstance.ledgerLoansLoanIdEventsEventIdGet(loanId, eventId);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsApi#ledgerLoansLoanIdEventsEventIdGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **eventId** | [**Long**](.md)| The unique identifier of an Event |

### Return type

[**Event**](Event.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="ledgerLoansLoanIdEventsGet"></a>
# **ledgerLoansLoanIdEventsGet**
> Events ledgerLoansLoanIdEventsGet(loanId, eventType, beforeEventId, fromEventId, since, before, size, sortOrder)

Read collection of events against a specific loan. With no parameters returns events since start of current day.

### Example
```java
// Import classes:
//import com.os.client.invoker.ApiClient;
//import com.os.client.invoker.ApiException;
//import com.os.client.invoker.Configuration;
//import com.os.client.invoker.auth.*;
//import com.os.client.api.EventsApi;

ApiClient defaultClient = Configuration.getDefaultApiClient();

// Configure OAuth2 access token for authorization: stage_auth
OAuth stage_auth = (OAuth) defaultClient.getAuthentication("stage_auth");
stage_auth.setAccessToken("YOUR ACCESS TOKEN");

EventsApi apiInstance = new EventsApi();
String loanId = "loanId_example"; // String | The unique identifier of a loan
List<EventType> eventType = Arrays.asList(new EventType()); // List<EventType> | Filter by event type
Long beforeEventId = 789L; // Long | 
Long fromEventId = 789L; // Long | 
OffsetDateTime since = new OffsetDateTime(); // OffsetDateTime | Events (since) timestamp UTC
OffsetDateTime before = new OffsetDateTime(); // OffsetDateTime | Events (before) timestamp UTC
Integer size = 56; // Integer | Number of events to be returned. Can be used to facilitate paging
SortOrder sortOrder = new SortOrder(); // SortOrder | Sort events by order of creation ascending or descending. Defaults to ASCENDING
try {
    Events result = apiInstance.ledgerLoansLoanIdEventsGet(loanId, eventType, beforeEventId, fromEventId, since, before, size, sortOrder);
    System.out.println(result);
} catch (ApiException e) {
    System.err.println("Exception when calling EventsApi#ledgerLoansLoanIdEventsGet");
    e.printStackTrace();
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **loanId** | [**String**](.md)| The unique identifier of a loan |
 **eventType** | [**List&lt;EventType&gt;**](EventType.md)| Filter by event type | [optional]
 **beforeEventId** | **Long**|  | [optional]
 **fromEventId** | **Long**|  | [optional]
 **since** | [**OffsetDateTime**](.md)| Events (since) timestamp UTC | [optional]
 **before** | [**OffsetDateTime**](.md)| Events (before) timestamp UTC | [optional]
 **size** | **Integer**| Number of events to be returned. Can be used to facilitate paging | [optional]
 **sortOrder** | [**SortOrder**](.md)| Sort events by order of creation ascending or descending. Defaults to ASCENDING | [optional]

### Return type

[**Events**](Events.md)

### Authorization

[stage_auth](../README.md#stage_auth)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

