package apps.xenione.com.demoloader.presentation.use_cases;
/*
Copyright 30/04/2017 Eugeni Josep Senent i Gabriel

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

import java.util.concurrent.Callable;

public abstract class UseCase<IN, OUT> implements Callable<OUT> {

    IN param;

    public abstract <T extends ParamBuilder<IN>> T getParamBuilder();

    public abstract static class ParamBuilder<IN> {

        private UseCase<IN, ?> useCase;

        public ParamBuilder(UseCase<IN, ?> useCase) {
            this.useCase = useCase;
        }

        protected abstract IN buildParams();

        public UseCase<IN, ?> apply() {
            useCase.param = buildParams();
            return useCase;
        }
    }
}
