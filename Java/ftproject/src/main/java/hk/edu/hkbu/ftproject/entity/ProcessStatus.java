package hk.edu.hkbu.ftproject.entity;

import io.jmix.core.metamodel.datatype.EnumClass;

import org.springframework.lang.Nullable;


public enum ProcessStatus implements EnumClass<String> {

    DRAFT("D"),
    PROCESSING("P"),
    COMPLETE("C");

    private final String id;

    ProcessStatus(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    @Nullable
    public static ProcessStatus fromId(String id) {
        for (ProcessStatus at : ProcessStatus.values()) {
            if (at.getId().equals(id)) {
                return at;
            }
        }
        return null;
    }
}