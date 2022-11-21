package mehmetari.enocademov1.core;


import mehmetari.enocademov1.dtos.worker.response.WorkerListResponse;
import mehmetari.enocademov1.dtos.worker.response.WorkerResponse;
import mehmetari.enocademov1.entity.Worker;



public class WorkerModel {

    public static WorkerResponse toWorkerResponse(Worker worker){

        WorkerResponse workerResponse = new WorkerResponse();
        workerResponse.setId(worker.getId());
        workerResponse.setFirstName(worker.getFirstName());
        workerResponse.setLastName(worker.getLastName());
        workerResponse.setCompanyId(worker.getCompany().getId());
        return workerResponse;
    }

    public static WorkerListResponse toWorkerListResponse(Worker worker){
        WorkerListResponse workerListResponse = new WorkerListResponse();
        workerListResponse.setId(worker.getId());
        workerListResponse.setFirstName(worker.getFirstName());
        workerListResponse.setLastName(worker.getLastName());
        return workerListResponse;
    }

}
