package site.carrenov.app.model.annotation;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import lombok.Getter;
import site.carrenov.app.model.annotation.util.ReflexionUtil;

@Getter
public class MapperConvert<D, E> {

    private static final Logger LOGGER = LoggerFactory.getLogger(MapperConvert.class);

    Set<MapperFieldAnnotation> fields;
    
    private Class<D> origin;
    private Class<E> destination;

    public MapperConvert(Class<D> origin, Class<E> destination) {
        fields = new LinkedHashSet<>();
        this.origin = origin;
        this.destination = destination;
    }    

    public E convertToDestiny( D o) {
        fields = ReflexionUtil.fieldsByAnnotation(o, MapperAnnotation.class);
        return createDestiny();
    }
    
    public D convertToOrigin( E o) {
        fields = ReflexionUtil.fieldsByAnnotation(o, MapperAnnotation.class);
        return createOrigin();
    }    
    
    private E createDestiny() {
        LOGGER.debug("destiny clazzName: {}", destination);
        E newInstance = null;
        try {
            newInstance = ReflexionUtil.createNewInstance(destination);
            LOGGER.debug("new object: {}", newInstance);
            for (MapperFieldAnnotation fieldM : fields) {
                newInstance = ReflexionUtil.setAttribute(newInstance, fieldM);
            }
        } catch (Exception e) {
            LOGGER.debug("Error New Instance Reflexion Class {}", e.getMessage());
        }
        LOGGER.debug("destiny object: {}", newInstance);
        return newInstance;
    }
    
    private D createOrigin() {
        LOGGER.debug("destiny clazzName: {}", origin);
        D newInstance = null;
        try {
            newInstance = ReflexionUtil.createNewInstance(origin);
            LOGGER.debug("new object: {}", newInstance);
            for (MapperFieldAnnotation fieldM : fields) {
                newInstance = ReflexionUtil.setAttribute(newInstance, fieldM);
            }
        } catch (Exception e) {
            LOGGER.debug("Error New Instance Reflexion Class {}", e.getMessage());
        }
        LOGGER.debug("destiny object: {}", newInstance);
        return newInstance;
    }    
    
    public List<E> convertListToDestiny( List<D> o) {
        List<E> newList = new ArrayList<>();
        for (D obj : o) {
            newList.add(convertToDestiny(obj));
        }
        return newList;
    }
    
    public List<D> convertListToOrigin( List<E> o) {
        List<D> newList = new ArrayList<>();
        for (E obj : o) {
            newList.add(convertToOrigin(obj));
        }
        return newList;
    }

}
