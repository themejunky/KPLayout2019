/*
 * Copyright (C) 2012 The Android Open Source Project
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

#ifndef LATINIME_GESTURE_SUGGEST_POLICY_FACTORY_H
#define LATINIME_GESTURE_SUGGEST_POLICY_FACTORY_H

#include "defines.h"
#include "../typing/typing_suggest_policy.h"
namespace latinime {

class SuggestPolicy;

class GestureSuggestPolicyFactory {
 public:
    static void setGestureSuggestPolicyFactoryMethod(const SuggestPolicy *(*factoryMethod)()) {
        sGestureSuggestFactoryMethod = factoryMethod;
    }

    static const SuggestPolicy *getGestureSuggestPolicy() {
        if (!sGestureSuggestFactoryMethod) {
            return TypingSuggestPolicy::getInstance();
        }
        return sGestureSuggestFactoryMethod();
    }

 private:
    DISALLOW_COPY_AND_ASSIGN(GestureSuggestPolicyFactory);
    static const SuggestPolicy *(*sGestureSuggestFactoryMethod)();
};
} // namespace latinime
#endif // LATINIME_GESTURE_SUGGEST_POLICY_FACTORY_H
