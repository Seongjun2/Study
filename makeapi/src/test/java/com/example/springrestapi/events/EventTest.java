package com.example.springrestapi.events;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(JUnitParamsRunner.class)
public class EventTest {

    @Test
    @Parameters
    public void testFree(int basePrice, int maxPrice, boolean isFree){

        //given
        Event event = Event.builder()
                .basePrice(basePrice)
                .maxPrice(maxPrice)
                .build();

        //when
        event.update();

        //then
        assertThat(event.isFree()).isEqualTo(isFree);
    }

    private Object[] parametersForTestFree(){
        return new Object[]{
                new Object[]{0,0,true},
                new Object[]{100,0,false},
                new Object[]{0,100,false}
        };
    }

    @Test
    @Parameters
    public void testOffline(String location, boolean isOffline){

        //given
        Event event = Event.builder()
                .location("StartUp Factory")
                .build();

        //when
        event.update();


        //then
        assertThat(event.isOffline()).isTrue();
    }
    private Object[] parametersForTestOffline(){
        return new Object[]{
                new Object[]{"강남",true},
                new Object[]{null,false},
                new Object[]{" ",false}
        };
    }
    /*
    JunitParams 를 적용해 테스트 코드 작성.
    @Parameters 어노테이션을 사용하면 파라미터화 된 입력값을 쓸 수 있음.

    ㅠㅏ라미터화된 입력값은 코드 상에 명시해야 함.
    parametersFor + [테스트명] 으로 작성하면 자동적으로 테스트명으로 작성된 테스트 메서드에 해당 입력값이 매칭 됨.
    또한 @parameters(method="테스트명" 형태로 직접 지정해서 쓸 수 있음.
     */
}

