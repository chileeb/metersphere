package io.metersphere.base.mapper.ext;

import io.metersphere.api.dto.QueryAPIReportRequest;
import io.metersphere.api.dto.datacount.ExecutedCaseInfoResult;
import io.metersphere.base.domain.ApiDefinitionExecResult;
import io.metersphere.base.domain.ApiDefinitionExecResultExpand;
import io.metersphere.base.domain.ApiDefinitionExecResultWithBLOBs;
import io.metersphere.task.dto.TaskCenterRequest;
import io.metersphere.track.dto.PlanReportCaseDTO;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Param;

import java.util.Collection;
import java.util.List;

public interface ExtApiDefinitionExecResultMapper {

    List<ApiDefinitionExecResultExpand> list(@Param("request") QueryAPIReportRequest request);

    void deleteByResourceId(String id);

    ApiDefinitionExecResultWithBLOBs selectMaxResultByResourceId(String resourceId);

    ApiDefinitionExecResultWithBLOBs selectMaxResultByResourceIdAndType(String resourceId, String type);


    long countByProjectIDAndCreateInThisWeek(@Param("projectId") String projectId, @Param("firstDayTimestamp") long firstDayTimestamp, @Param("lastDayTimestamp") long lastDayTimestamp);

    long countByTestCaseIDInProject(String projectId);

    List<ExecutedCaseInfoResult> findFaliureCaseInfoByProjectIDAndExecuteTimeAndLimitNumber(@Param("projectId") String projectId, @Param("startTimestamp") long startTimestamp);

    List<ExecutedCaseInfoResult> findFaliureCaseInTestPlanByProjectIDAndExecuteTimeAndLimitNumber(@Param("projectId") String projectId, @Param("selectFunctionCase") boolean selectFunctionCase, @Param("startTimestamp") long startTimestamp);

    String selectExecResult(String resourceId);

    ApiDefinitionExecResultWithBLOBs selectPlanApiMaxResultByTestIdAndType(String resourceId, String type);

    List<ApiDefinitionExecResult> selectStatusByIdList(@Param("ids") Collection<String> values);

    List<ApiDefinitionExecResult> selectApiResultByProjectId(String projectId);

    List<PlanReportCaseDTO> selectForPlanReport(@Param("ids") List<String> apiReportIds);

    void update(@Param("ids") List<String> ids);

    @InsertProvider(type = ExtApiDefinitionExecResultProvider.class, method = "insertListSql")
    void sqlInsert(List<ApiDefinitionExecResult> list);

    List<ApiDefinitionExecResult> findByProjectIds(@Param("request") TaskCenterRequest request);

    List<String> selectDistinctStatusByReportId(String reportId);

    String selectResourceId(String id);

    List<ApiDefinitionExecResultWithBLOBs> selectRerunResult(@Param("reportId") String reportId);
}
