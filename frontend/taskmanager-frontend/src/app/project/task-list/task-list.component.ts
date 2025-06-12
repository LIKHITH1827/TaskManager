import { Component, inject } from '@angular/core';
import { Task } from '../../task.model';
import { DatePipe, AsyncPipe } from '@angular/common';
import { TaskService } from '../../task.service';
import { TaskFormComponent } from '../task-form/task-form.component';
import { Observable } from 'rxjs';


const emptyTask={
  name:"",
  description:"",
  dueDate: new Date(),
 completed: false,
 project:0,
 id:0,
};


@Component({
  selector: 'app-task-list',
  standalone: true,
  imports: [DatePipe,AsyncPipe,TaskFormComponent],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {

  tasks:Task[]=[];
   formType: "UPDATE" | "CREATE" = 'CREATE';
  showModal:boolean=false;
  selectedTask : Task = emptyTask;
  tasks$!:Observable<Task[]>


  private taskService= inject(TaskService);

  constructor(){
    this.updateTasks();
  }

  updateTasks(){
    this.tasks$ = this.taskService.getTasks();
  }

handleCheckBox(id:number){
  //console.log(id);
 const checkedIndex = this.tasks.findIndex((task)=>task.id===id);
 const updatedTask= this.tasks[checkedIndex];
 updatedTask.completed=!updatedTask.completed;
 this.tasks=this.taskService.updateTask(updatedTask);

}

updateTask(task:Task){
this.selectedTask= task;

this.formType='UPDATE';
this.showModal=true;




}



addNewTask(){
  this.selectedTask=emptyTask;
  this.formType='CREATE';
  this.showModal=true;
}

deleteTask(id:number){
this.tasks=this.taskService.deleteTask(id);
}



}
