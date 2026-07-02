import { CommonModule } from '@angular/common';
import { Component, inject, OnInit } from '@angular/core';
import { FormBuilder, FormControl, FormGroup, ReactiveFormsModule, Validators } from '@angular/forms';
import { Title } from '@angular/platform-browser';
import { Observable, startWith, Subject, switchMap } from 'rxjs';
import { Editeur } from '../../model/editeur';
import { EditeurService } from '../../service/editeur-service';

@Component({
  selector: 'app-editeur-page',
  imports: [ CommonModule, ReactiveFormsModule ],
  templateUrl: './editeur-page.html',
  styleUrl: './editeur-page.css',
})
export class EditeurPage implements OnInit {
  private titleService: Title = inject(Title);
  private editeurService: EditeurService = inject(EditeurService);

  private refresh$: Subject<void> = new Subject<void>();
  protected editeurs$!: Observable<Editeur[]>;

  // Reactive Forms
  private formBuilder: FormBuilder = inject(FormBuilder);
  protected formEditeur!: FormGroup;
  protected formCtrlNom!: FormControl;
  protected formCtrlPays!: FormControl;
  protected editingEditeurId: number | undefined = 0;

  ngOnInit(): void {
    this.titleService.setTitle("Liste des éditeurs");

    this.editeurs$ = this.refresh$.pipe(
      startWith(0), // Initialisation => forcer le chargement une première fois
      switchMap(() => this.editeurService.findAll()) // Transformer au moment du next()
    );

    // Reactive Form
    this.formCtrlNom = this.formBuilder.control('', Validators.required);
    this.formCtrlPays = this.formBuilder.control('', Validators.required);

    this.formEditeur = this.formBuilder.group({
      // Ajout des différents contrôles == input, select, etc.
      // libelle: this.formBuilder.control('', Validators.required)
      nom: this.formCtrlNom,
      pays: this.formCtrlPays
    });
  }

  private reload() {
    this.refresh$.next();
  }

  protected addOrUpdate() {
    // const matiere: Matiere = {
    //   libelle: this.formCtrlLibelle.value
    // };

    const editeur: Editeur = this.formEditeur.getRawValue();

    if (this.editingEditeurId) {
      editeur.id = this.editingEditeurId;
      this.editeurService.update(editeur).subscribe(() => this.reload());
    }

    else {
      this.editeurService.add(editeur).subscribe(() => this.reload());
    }

    this.formEditeur.reset();
    this.editingEditeurId = 0;
  }

  protected edit(editeur: Editeur) {
    this.editingEditeurId = editeur.id;
    this.formCtrlNom.setValue(editeur.nom);
    this.formCtrlPays.setValue(editeur.pays);
  }

  protected remove(editeur: Editeur) {
    this.editeurService.remove(editeur).subscribe(() => this.reload());
  }
}
