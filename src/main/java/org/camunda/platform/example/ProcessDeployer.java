/*
 * Copyright Camunda Services GmbH and/or licensed to Camunda Services GmbH
 * under one or more contributor license agreements. See the NOTICE file
 * distributed with this work for additional information regarding copyright
 * ownership. Camunda licenses this file to you under the Apache License,
 * Version 2.0; you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.camunda.platform.example;

import org.camunda.bpm.engine.RepositoryService;
import org.camunda.bpm.quarkus.engine.extension.event.CamundaEngineStartupEvent;

import javax.enterprise.event.Observes;
import javax.inject.Inject;

public class ProcessDeployer {

  @Inject
  public RepositoryService repositoryService;

  // Method is called as soon as the Process Engine is running
  public void deployProcess(@Observes CamundaEngineStartupEvent startupEvent) {
    // Create a new deployment
    repositoryService.createDeployment()
        .addClasspathResource("process.bpmn") // Filename of the process model
        .enableDuplicateFiltering(true) // No redeployment when process model remains unchanged
        .deploy();
  }

}