package vn.com.mbbank.utils;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;

import java.io.IOException;

/**
 * @ClassName HibernateProxyTypeAdapter
 * @Description :
 * @Author PC
 * @Date 2018/10/29 10:23
 **/
public class HibernateProxyTypeAdapter extends TypeAdapter<HibernateProxy> {

    public static final TypeAdapterFactory FACTORY = new TypeAdapterFactory() {
        @Override
        public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> typeToken) {
            return (HibernateProxy.class.isAssignableFrom(typeToken.getRawType())? (TypeAdapter) new HibernateProxyTypeAdapter(gson):null);
        }
    };

    private final Gson context;
    private HibernateProxyTypeAdapter(Gson gson){
        this.context = gson;
    }
    @Override
    public void write(JsonWriter jsonWriter, HibernateProxy hibernateProxy) throws IOException {
        if (hibernateProxy==null){
            jsonWriter.nullValue();
            return;
        }
        Class<?> baseType = Hibernate.getClass(hibernateProxy);
        TypeAdapter delegate = context.getAdapter(TypeToken.get(baseType));
        Object unproxiedValue = ((HibernateProxy) hibernateProxy).getHibernateLazyInitializer()
                .getImplementation();
        delegate.write(jsonWriter,unproxiedValue);
    }

    @Override
    public HibernateProxy read(JsonReader jsonReader) throws IOException {
        throw new UnsupportedOperationException("Not support");
    }
}