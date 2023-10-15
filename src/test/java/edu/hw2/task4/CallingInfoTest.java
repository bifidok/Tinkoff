package edu.hw2.task4;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingInfoTest {
    @Test
    @DisplayName("Вызов метода из CallingInfoTest")
    public void callingInfo_ShouldReturnThisClassAndMethodName() {
        var info = Call.callingInfo();

        String className = this.getClass().getName();
        String methodName = new Object() {}
            .getClass()
            .getEnclosingMethod()
            .getName();

        assertThat(info.className()).isEqualTo(className);
        assertThat(info.methodName()).isEqualTo(methodName);
    }
}
