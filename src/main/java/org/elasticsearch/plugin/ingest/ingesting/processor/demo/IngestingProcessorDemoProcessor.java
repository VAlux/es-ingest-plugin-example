/*
 * Copyright [2018] [Oleksandr Voievodin]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.elasticsearch.plugin.ingest.ingesting.processor.demo;

import org.elasticsearch.ingest.AbstractProcessor;
import org.elasticsearch.ingest.IngestDocument;
import org.elasticsearch.ingest.Processor;

import java.util.Locale;
import java.util.Map;

import static org.elasticsearch.ingest.ConfigurationUtils.readStringProperty;

public class IngestingProcessorDemoProcessor extends AbstractProcessor {

  public static final String TYPE = "ingesting_processor_demo";

  private final String field;
  private final String targetField;

  public IngestingProcessorDemoProcessor(String tag, String sourceField, String targetField) {
    super(tag);
    this.field = sourceField;
    this.targetField = targetField;
  }

  @Override
  public void execute(IngestDocument ingestDocument) {
    final String content = ingestDocument.getFieldValue(field, String.class);
    final String processed = content.toUpperCase(Locale.getDefault()) + " Processed!"; // simple PoC modification here.
    ingestDocument.setFieldValue(targetField, processed);
  }

  @Override
  public String getType() {
    return TYPE;
  }

  public static final class Factory implements Processor.Factory {

    @Override
    public IngestingProcessorDemoProcessor create(Map<String, Processor.Factory> factories,
                                                  String tag,
                                                  Map<String, Object> config) {

      String field = readStringProperty(TYPE, tag, config, "field");
      String targetField = readStringProperty(
          TYPE,
          tag,
          config,
          "target_field",
          "default_field_name");

      return new IngestingProcessorDemoProcessor(tag, field, targetField);
    }
  }
}
