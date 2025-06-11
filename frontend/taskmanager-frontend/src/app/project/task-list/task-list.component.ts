import { Component, inject } from '@angular/core';
import { Task } from '../../task.model';
import { DatePipe } from '@angular/common';
import { TaskService } from '../../task.service';
import { TaskFormComponent } from '../task-form/task-form.component';

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
  imports: [DatePipe,TaskFormComponent],
  templateUrl: './task-list.component.html',
  styleUrl: './task-list.component.css'
})
export class TaskListComponent {

  tasks:Task[];
   formType: "UPDATE" | "CREATE" = 'CREATE';
  showModal:boolean=false;
  selectedTask : Task = emptyTask;

  private taskService= inject(TaskService);

  constructor(){
    this.tasks=this.taskService.getTasks();
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


deleteTask(id:number){
this.tasks=this.taskService.deleteTask(id);
}



}
