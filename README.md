# Rx-Soap [![Build Status](https://travis-ci.org/moltak/Rx-Soap.svg?branch=master)](https://travis-ci.org/moltak/Rx-Soap) [![Release](https://img.shields.io/github/tag/moltak/rx-soap.svg?label=Release)](https://jitpack.io/#moltak/rx-soap)
### Rx-Soap library for reactive java and android.

Add it to your build.gradle with:
```gradle
repositories {
    maven { url "https://jitpack.io" }
}
```

```gradle
dependencies {
	compile 'com.github.moltak:rx-soap:1.0'
}
```

### How to use
##### Send request
``` java
BaseSoapAdapter adapter = new SoapAdapterFactory.Builder<>()
    .setEndpoint("http://soap-endpoint.com/")
    .setWebserviceName("/WebServices/Soap.asmx")
    .enableDebug() // Enable debug mode for print log
    .setTimeout(5000) // Default timeout(5 seconds)
    .setLogLevel(BaseSoapAdapter.LogLevel.FULL) // It prints logs.
    .setSoapPrinter(new DefaultSoapPrinter()) // Default soap printer(xml, http response) codes
    .build();

Observable<GetObjectResponse> o
        = adapter.create(new GetObjectRequest("name"))
        .async(GetObjectResponse.class);
o.subscribe(new Action1<GetObjectResponse>() {
    @Override
    public void call(GetObjectResponse results) {
        // There are results.
    }
}, new Action1<Throwable>() {
    @Override
    public void call(Throwable throwable) {

    }
});
```

##### Request model
```java
public class GetObjectRequest implements BaseRequest {
    private final String name;

    public GetObjectRequest(String name) {
        this.name = name;
    }

    @Override
    public String getRequestName() {
        return "get_info"; // This is an action name for soap request.
    }

    @Override
    public Map<String, String> getParams() throws IllegalAccessException, InvocationTargetException {
        return ParameterBaker.bake(this);
    }

    public String getName() {
        return name;
    }
}

```

##### Response model. Annotation based parsing.
```java
public class GetObjectResponse {
    @JSoapResField(name = "name")
    private String name;

    @JSoapResField(name = "gender")
    private String gender;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }
}

```


##### Run tests using gradle
``` bash
./gradlew test
```

##### Run tests using mvn
``` bash
mvn test
```


License
-------
    Copyright 2015 Jung Kyungho.

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
