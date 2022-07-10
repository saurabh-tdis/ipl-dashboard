export class AppResponse<T>{
    status: string;
    message: string;
    payload: T;
}