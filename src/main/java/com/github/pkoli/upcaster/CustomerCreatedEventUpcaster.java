package com.github.pkoli.upcaster;

import com.github.pkoli.events.CustomerCreatedEvent;
import org.axonframework.serialization.SimpleSerializedType;
import org.axonframework.serialization.upcasting.event.IntermediateEventRepresentation;
import org.axonframework.serialization.upcasting.event.SingleEventUpcaster;

public class CustomerCreatedEventUpcaster extends SingleEventUpcaster {

    private static SimpleSerializedType targetType = new SimpleSerializedType(CustomerCreatedEvent.class.getTypeName(),
            "1.0");

    @Override
    protected boolean canUpcast(IntermediateEventRepresentation intermediateRepresentation) {
        return intermediateRepresentation.getType().equals(targetType);
    }

    @Override
    protected IntermediateEventRepresentation doUpcast(IntermediateEventRepresentation intermediateRepresentation) {
        return intermediateRepresentation.upcastPayload(
                new SimpleSerializedType(targetType.getName(), "2.0"),
                org.dom4j.Document.class,
                document -> {
                    document.getRootElement().addElement("salary");
                    document.getRootElement().element("salary").setData(0L);
                    return document;
                }
        );
    }
}
