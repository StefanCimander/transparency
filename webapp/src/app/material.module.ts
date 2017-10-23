import { NgModule } from '@angular/core';
import {
    MatButtonModule,
    MatCardModule,
    MatIconModule,
    MatMenuModule,
    MatRadioModule,
    MatToolbarModule
} from '@angular/material';

@NgModule({
    imports: [
        MatButtonModule,
        MatCardModule,
        MatIconModule,
        MatMenuModule,
        MatRadioModule,
        MatToolbarModule
    ],
    exports: [
        MatButtonModule,
        MatCardModule,
        MatIconModule,
        MatMenuModule,
        MatRadioModule,
        MatToolbarModule
    ],
})
export class MaterialModule { }
