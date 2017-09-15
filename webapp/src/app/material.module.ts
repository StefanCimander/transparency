import { NgModule } from '@angular/core';
import { MdButtonModule, MdCardModule, MdIconModule, MdMenuModule, MdToolbarModule } from '@angular/material';

@NgModule({
  imports: [
    MdButtonModule,
    MdCardModule,
    MdIconModule,
    MdMenuModule,
    MdToolbarModule
  ],
  exports: [
    MdButtonModule,
    MdCardModule,
    MdIconModule,
    MdMenuModule,
    MdToolbarModule
  ],
})
export class MaterialModule { }
