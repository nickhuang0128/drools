package org.kie.dmn.feel.runtime.functions.extended;

import org.kie.dmn.api.feel.runtime.events.FEELEvent;
import org.kie.dmn.feel.runtime.events.InvalidParametersEvent;
import org.kie.dmn.feel.runtime.functions.BaseFEELFunction;
import org.kie.dmn.feel.runtime.functions.FEELFnResult;
import org.kie.dmn.feel.runtime.functions.ParameterName;

import java.math.BigDecimal;

public class EvenFunction 
        extends BaseFEELFunction {
    public static final EvenFunction INSTANCE = new EvenFunction();

    private static final BigDecimal TWO = BigDecimal.valueOf(2);

    EvenFunction() {
        super("even");
    }

    public FEELFnResult<Boolean> invoke(@ParameterName( "number" ) BigDecimal number) {
        if ( number == null ) {
            return FEELFnResult.ofError( new InvalidParametersEvent( FEELEvent.Severity.ERROR, "number", "cannot be null" ) );
        }
        if ( number.remainder( BigDecimal.ONE ).signum() != 0 ) {
            return FEELFnResult.ofError( new InvalidParametersEvent( FEELEvent.Severity.ERROR, "number", "cannot have non-zero fractional part" ) );
        }
        return FEELFnResult.ofResult( BigDecimal.ZERO.compareTo( number.remainder(TWO) ) == 0);
    }
}
