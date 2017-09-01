import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DependenciesPreviewComponent } from './dependencies-preview.component';

describe('DependenciesPreviewComponent', () => {
  let component: DependenciesPreviewComponent;
  let fixture: ComponentFixture<DependenciesPreviewComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DependenciesPreviewComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DependenciesPreviewComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should be created', () => {
    expect(component).toBeTruthy();
  });
});
