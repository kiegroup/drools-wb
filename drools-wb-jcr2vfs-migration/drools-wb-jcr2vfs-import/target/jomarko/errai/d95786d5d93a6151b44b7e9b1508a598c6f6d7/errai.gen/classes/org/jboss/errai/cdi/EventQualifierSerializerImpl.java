package org.jboss.errai.cdi;

import java.util.function.Function;
import javax.inject.Named;
import org.jboss.errai.common.client.util.AnnotationPropertyAccessorBuilder;
import org.jboss.errai.common.client.util.SharedAnnotationSerializer;
import org.jboss.errai.enterprise.client.cdi.EventQualifierSerializer;
import org.jboss.errai.ioc.client.api.ReplyTo;
import org.jboss.errai.ioc.client.api.ToSubject;
import org.kie.api.cdi.KBase;
import org.kie.api.cdi.KContainer;
import org.kie.api.cdi.KReleaseId;
import org.kie.api.cdi.KSession;
import org.uberfire.preferences.shared.annotations.WorkbenchPreference;

public class EventQualifierSerializerImpl extends EventQualifierSerializer { public EventQualifierSerializerImpl() {
    serializers.put("org.jboss.errai.ioc.client.api.ReplyTo", AnnotationPropertyAccessorBuilder.create().with("value", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((ReplyTo) anno).value());
      }
    }).build());
    serializers.put("org.kie.api.cdi.KSession", AnnotationPropertyAccessorBuilder.create().with("name", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((KSession) anno).name());
      }
    }).with("value", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((KSession) anno).value());
      }
    }).build());
    serializers.put("org.kie.api.cdi.KReleaseId", AnnotationPropertyAccessorBuilder.create().with("groupId", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((KReleaseId) anno).groupId());
      }
    }).with("artifactId", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((KReleaseId) anno).artifactId());
      }
    }).with("version", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((KReleaseId) anno).version());
      }
    }).build());
    serializers.put("javax.inject.Named", AnnotationPropertyAccessorBuilder.create().with("value", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((Named) anno).value());
      }
    }).build());
    serializers.put("org.uberfire.preferences.shared.annotations.WorkbenchPreference", AnnotationPropertyAccessorBuilder.create().with("identifier", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((WorkbenchPreference) anno).identifier());
      }
    }).with("bundleKey", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((WorkbenchPreference) anno).bundleKey());
      }
    }).build());
    serializers.put("org.kie.api.cdi.KBase", AnnotationPropertyAccessorBuilder.create().with("name", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((KBase) anno).name());
      }
    }).with("value", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((KBase) anno).value());
      }
    }).build());
    serializers.put("org.kie.api.cdi.KContainer", AnnotationPropertyAccessorBuilder.create().with("name", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((KContainer) anno).name());
      }
    }).build());
    serializers.put("org.jboss.errai.ioc.client.api.ToSubject", AnnotationPropertyAccessorBuilder.create().with("value", new Function() {
      public Object apply(final Object anno) {
        return SharedAnnotationSerializer.stringify(((ToSubject) anno).value());
      }
    }).build());
  }

}