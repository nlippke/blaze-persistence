/*
 * Copyright 2014 - 2017 Blazebit.
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

package com.blazebit.persistence.view.impl.entity;

import com.blazebit.persistence.view.impl.change.DirtyChecker;
import com.blazebit.persistence.view.impl.update.UpdateContext;

/**
 *
 * @author Christian Beikov
 * @since 1.2.0
 */
public class LoadOnlyEntityToEntityMapper extends AbstractEntityToEntityMapper {

    public LoadOnlyEntityToEntityMapper(EntityLoaderFetchGraphNode<?> entityLoaderFetchGraphNode) {
        super(entityLoaderFetchGraphNode);
    }

    @Override
    public Object applyToEntity(UpdateContext context, Object entity, Object dirtyEntity) {
        if (dirtyEntity == null) {
            return null;
        }

        Object id = entityLoaderFetchGraphNode.getEntityId(context, dirtyEntity);
        return entityLoaderFetchGraphNode.toEntity(context, id);
    }

    @Override
    public DirtyChecker<?> getDirtyChecker() {
        return null;
    }
}