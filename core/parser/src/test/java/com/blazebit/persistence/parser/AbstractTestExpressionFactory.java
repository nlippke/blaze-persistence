/*
 * Copyright 2014 - 2023 Blazebit.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.blazebit.persistence.parser;

import com.blazebit.persistence.parser.expression.AbstractExpressionFactory;
import com.blazebit.persistence.parser.expression.ExpressionFactory;
import org.antlr.v4.runtime.atn.PredictionMode;

import java.util.Collections;
import java.util.Map;
import java.util.logging.Level;

/**
 *
 * @author Moritz Becker
 */
public abstract class AbstractTestExpressionFactory extends AbstractExpressionFactory {

    public AbstractTestExpressionFactory(Map<String, FunctionKind> functions, boolean optimize) {
        this(functions, Collections.EMPTY_MAP, Collections.EMPTY_MAP, optimize);
    }
    
    public AbstractTestExpressionFactory(Map<String, FunctionKind> functions, Map<String, Class<?>> entityTypes, Map<String, Class<Enum<?>>> enumTypes, boolean optimize) {
        super(functions, entityTypes, enumTypes, enumTypes, optimize);
    }

    public AbstractTestExpressionFactory(Map<String, FunctionKind> functions, Map<String, Class<?>> entityTypes, Map<String, Class<Enum<?>>> enumTypes, Map<String, Class<Enum<?>>> enumTypesForLiterals, boolean optimize) {
        super(functions, entityTypes, enumTypes, enumTypesForLiterals, optimize);
    }

    @Override
    protected void configureLexer(JPQLNextLexer lexer) {
        lexer.removeErrorListeners();
        lexer.addErrorListener(ERR_LISTENER);
    }

    @Override
    protected void configureParser(JPQLNextParser parser) {
        if (LOG.isLoggable(Level.FINEST)) {
            parser.setTrace(true);
        }
        
        parser.removeErrorListeners();
        parser.addErrorListener(ERR_LISTENER);
        parser.getInterpreter().setPredictionMode(PredictionMode.LL_EXACT_AMBIG_DETECTION);
    }

    @Override
    public <T extends ExpressionFactory> T unwrap(Class<T> clazz) {
        if (clazz.isAssignableFrom(AbstractTestExpressionFactory.class)) {
            return (T) this;
        }
        return null;
    }
}
