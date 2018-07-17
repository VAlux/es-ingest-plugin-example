# Elasticsearch ingesting-processor-demo

This is a demo elasticsearch processor plugin
Introduces a simple PoC-like modification logic to the ingesting pipeline 

## Usage


```
PUT _ingest/pipeline/ingesting-processor-demo-pipeline
{
  "description": "A pipeline to do whatever",
  "processors": [
    {
      "ingesting_processor_demo" : {
        "field" : "my_field"
      }
    }
  ]
}

PUT /my-index/my-type/1?pipeline=ingesting-processor-demo-pipeline
{
  "my_field" : "Some content"
}

GET /my-index/my-type/1
{
  "my_field": "SOME CONTENT Processed!"
}
```

## Setup

In order to install this plugin, you need to create a zip distribution first by running

```bash
gradle clean assemble
```

This will produce a zip file in `build/distributions`.

After building the zip file, you can install it like this

```bash
elasticsearch-plugin install file:///path/to/ingest-ingesting-processor-demo/build/distribution/ingest-ingesting-processor-demo-0.0.1-SNAPSHOT.zip
```

## Testing

### Run integration tests

```bash
gradle integTest
```

### Run Unit tests
```bash
gradle clean test
```

### Run Checkstyle
```$xslt
gradle check
```