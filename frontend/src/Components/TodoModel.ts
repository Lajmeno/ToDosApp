export interface ToDoModel{
    id: string;
    title: string;
    description: string;
    dateTime: string;
    status: string;
}

export interface ToDos{
    ToDoModels: Array<ToDoModel>;
}