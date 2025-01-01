package com.template.spring_mongodb.common;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QJsonConverter is a Querydsl query type for JsonConverter
 */
@Generated("com.querydsl.codegen.DefaultSupertypeSerializer")
public class QJsonConverter extends EntityPathBase<JsonConverter<?>> {

    private static final long serialVersionUID = 225869692L;

    public static final QJsonConverter jsonConverter = new QJsonConverter("jsonConverter");

    public final SimplePath<com.fasterxml.jackson.databind.ObjectMapper> objectMapper = createSimple("objectMapper", com.fasterxml.jackson.databind.ObjectMapper.class);

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QJsonConverter(String variable) {
        super((Class) JsonConverter.class, forVariable(variable));
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QJsonConverter(Path<? extends JsonConverter> path) {
        super((Class) path.getType(), path.getMetadata());
    }

    @SuppressWarnings({"all", "rawtypes", "unchecked"})
    public QJsonConverter(PathMetadata metadata) {
        super((Class) JsonConverter.class, metadata);
    }

}

