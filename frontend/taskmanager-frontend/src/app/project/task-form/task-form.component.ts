import { Component, EventEmitter, inject, Input, Output } from '@angular/core';
import { FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { FormBuilder } from '@angular/forms';
import { Task } from '../../task.model';
import { TaskService } from '../../task.service';

@Component({
  selector: 'app-task-form',
  standalone: true,
  imports: [ReactiveFormsModule],
  templateUrl: './task-form.component.html',
  styleUrl: './task-form.component.css'
})


export class TaskFormComponent {
  taskForm:FormGroup;

  //child to parent
  @Output() closePanel= new EventEmitter<'SUBMIT'>();

  @Input() currentTask: Task | null= null;
  @Input() formType: 'UPDATE' | 'CREATE' = 'CREATE';
  private taskService= inject(TaskService);

constructor(private fb: FormBuilder){
  this.taskForm= this.fb.group({
    name: ['',Validators.required],
    description: [''],
    dueDate : ['',Validators.required],
    id:[0],
    project: [0],


  })
}

handleSubmit(){
  if(this.taskForm.valid){
    const newTask: Task= {
      ...this.taskForm.value,
      dueDate: new Date(this.taskForm.value.dueDate),
      completed: false,
    };
    this.taskService.addTask(newTask);
    this.closePanel.emit('SUBMIT');
  }
}


}
