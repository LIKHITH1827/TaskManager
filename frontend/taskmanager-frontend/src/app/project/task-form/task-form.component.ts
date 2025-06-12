import { Component, EventEmitter, inject, Input, Output, SimpleChange, SimpleChanges } from '@angular/core';
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
  @Output() closePanel= new EventEmitter<'SUBMIT' | 'CANCEL'>();

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

ngOnChanges(changes: SimpleChanges){
if(changes['currentTask'] && changes['currentTask'].currentValue){
  const task=changes['currentTask'].currentValue as Task;

 const dueDateFormatted= task.dueDate ? new Date(task.dueDate).toISOString().substring(0,10): '';
 this.taskForm.patchValue({
...task,
  dueDate: dueDateFormatted
 });

}


}




handleSubmit(){
  if(this.taskForm.valid){
    const newTask: Task= {
      ...this.taskForm.value,
      dueDate: new Date(this.taskForm.value.dueDate),
      completed: this.formType==='UPDATE' ? this.taskForm.value.completed : false,
    };

    if(this.formType==='CREATE'){
     this.taskService.addTask(newTask).subscribe(()=>{
          this.closePanel.emit('SUBMIT');
     });

    }
    else{
      this.taskService.updateTask(newTask);
      this.closePanel.emit('SUBMIT');
    }

  }


  }

  handleCancel(){
    this.closePanel.emit('CANCEL');
  }
}



