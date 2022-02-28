export interface ToDoModel{
    id: string;
    title: string;
    content: string;
    dateTime: string;
    status: string;
}

export interface ToDos{
    ToDoModels: Array<ToDoModel>;
}