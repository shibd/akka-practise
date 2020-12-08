package com.baozi.akka.task.message;

import java.io.Serializable;

/**
 * @Author: baozi
 * @Date: 12/7/20 10:51 AM
 */
public class StartTaskGroupCmd implements Serializable {

    // 环境ID
    private String envId;

    // 任务组ID
    private String taskId;

    // 动作组Id
    private String actionGroupId;

    // 案例Id
    private String caseId;

    public StartTaskGroupCmd(String envId, String taskId, String actionGroupId, String caseId) {
        this.envId = envId;
        this.taskId = taskId;
        this.actionGroupId = actionGroupId;
        this.caseId = caseId;
    }

    @Override
    public String toString() {
        return "StartTaskGroupCmd{" +
                "envId='" + envId + '\'' +
                ", taskId='" + taskId + '\'' +
                ", actionGroupId='" + actionGroupId + '\'' +
                ", caseId='" + caseId + '\'' +
                '}';
    }

    // ... 动作组和案例相关属性带进去，或者查询

    public String actorId() {
        return envId + ":" + taskId;
    }

    public String getEnvId() {
        return envId;
    }

    public void setEnvId(String envId) {
        this.envId = envId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getActionGroupId() {
        return actionGroupId;
    }

    public void setActionGroupId(String actionGroupId) {
        this.actionGroupId = actionGroupId;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}
