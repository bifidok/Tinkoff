package edu.hw2;

import edu.hw2.Task4.Call;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CallingInfoTest {
    @Test
    @DisplayName("Вызов метода из JUnit")
    public void callingInfo_ShouldReturnThisClassAndMethodName() {
        var info = Call.callingInfo();

        assertThat(info.className()).isEqualTo("com.intellij.rt.junit.JUnitStarter");
        assertThat(info.methodName()).isEqualTo("main");
    }
}
