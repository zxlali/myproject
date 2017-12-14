package com.zxl.test.myproject.util;

import java.lang.annotation.Annotation;
import java.lang.reflect.AnnotatedElement;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

public final class AnnotationUtils {

  private AnnotationUtils() {}

  public static Annotation[] getMetaAnnotations(Annotation annotation) {
      return annotation == null ? new Annotation[0] : annotation.annotationType().getAnnotations();
  }

  public static <A extends Annotation> Annotation[] getMetaAnnotations(AnnotatedElement element,
          Class<A> annotationType) {
      if (element == null || annotationType == null)
          return new Annotation[0];

      return getMetaAnnotations(element.getAnnotation(annotationType));
  }

  public static Set<Annotation> getMetaAnnotations(AnnotatedElement element) {
      if (element == null)
          return Collections.emptySet();

      Set<Annotation> set = new LinkedHashSet<>();

      Annotation[] annotations = element.getAnnotations();
      for (Annotation anno : annotations) {
          Annotation[] metaAnnos = getMetaAnnotations(anno);
          Collections.addAll(set, metaAnnos);
      }

      return set;
  }

  public static <A extends Annotation> A findMetaAnnotation(Annotation annotation, Class<A> metaAnnotationType) {
      if (annotation == null || metaAnnotationType == null)
          return null;

      return annotation.annotationType().getAnnotation(metaAnnotationType);
  }

  public static <A extends Annotation> A findMetaAnnotation(AnnotatedElement element, Class<A> metaAnnotationType) {
      if (element == null || metaAnnotationType == null)
          return null;

      A metaAnnotation;
      Annotation[] annotations = element.getAnnotations();
      for (Annotation anno : annotations) {
          metaAnnotation = findMetaAnnotation(anno, metaAnnotationType);
          if (metaAnnotation != null)
              return metaAnnotation;
      }

      return null;
  }

  public static <A extends Annotation> A findAnnotation(AnnotatedElement element, Class<A> annotationType) {
      if (element == null || annotationType == null)
          return null;

      A annotation = element.getAnnotation(annotationType);
      if (annotation == null) {
          annotation = findMetaAnnotation(element, annotationType);
      }

      return annotation;
  }

  public static <A extends Annotation> A findAnnotation(AnnotatedElement[] elements, Class<A> annotationType) {
      if (elements == null || annotationType == null)
          return null;

      A annotation;
      for (AnnotatedElement element : elements) {
          annotation = findAnnotation(element, annotationType);
          if (annotation != null)
              return annotation;
      }

      return null;
  }

  public static <A extends Annotation> Set<A> findAnnotations(AnnotatedElement element, Class<A> annotationType) {
      if (element == null || annotationType == null)
          return Collections.emptySet();

      Set<A> set = new LinkedHashSet<>();

      A annotation;
      Annotation[] annotations = element.getAnnotations();
      for (Annotation anno : annotations) {
          if (anno.annotationType() == annotationType) {
              //noinspection unchecked
              set.add((A) anno);
          } else {
              annotation = findMetaAnnotation(anno, annotationType);
              if (annotation != null)
                  set.add(annotation);
          }
      }

      return set;
  }

  public static <A extends Annotation> Set<A> findAnnotations(AnnotatedElement[] elements, Class<A> annotationType) {
      if (elements == null || annotationType == null)
          return Collections.emptySet();

      Set<A> set = new LinkedHashSet<>();

      Set<A> annotations;
      for (AnnotatedElement element : elements) {
          annotations = findAnnotations(element, annotationType);
          if (!annotations.isEmpty())
              set.addAll(annotations);
      }

      return set;
  }
}
