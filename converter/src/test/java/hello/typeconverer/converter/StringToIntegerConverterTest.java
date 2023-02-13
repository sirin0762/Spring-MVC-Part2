package hello.typeconverer.converter;

import hello.typeconverer.type.IpPort;
import org.junit.jupiter.api.Test;
import org.springframework.core.convert.support.DefaultConversionService;

import static org.assertj.core.api.Assertions.assertThat;

class StringToIntegerConverterTest {

    @Test
    void StringToInteger() {
        StringToIntegerConverter converter = new StringToIntegerConverter();
        Integer result = converter.convert("10");
        assertThat(result).isEqualTo(10);
    }

    @Test
    void StringToIpPort() {
        StringToIpPortConverter converter = new StringToIpPortConverter();
        IpPort ipPort = new IpPort("127.0.0.0", 8080);
        assertThat(converter.convert("127.0.0.0:8080")).isEqualTo(ipPort);
    }

    @Test
    void defaultConversionServiceTest() {
        // 등록
        DefaultConversionService cs = new DefaultConversionService();
        cs.addConverter(new StringToIpPortConverter());
        cs.addConverter(new StringToIntegerConverter());
        cs.addConverter(new StringToIntegerConverter2());

        // 사용
        IpPort convert = cs.convert("127.0.0.0:8080", IpPort.class);
        assertThat(convert).isEqualTo(new IpPort("127.0.0.0", 8080));

        // 에러
        Integer integer = cs.convert("10", Integer.class);
        assertThat(integer).isEqualTo(10);
    }

}
