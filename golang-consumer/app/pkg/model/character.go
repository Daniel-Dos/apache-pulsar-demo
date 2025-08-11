package model

type Character struct {
	Name     string `json:"name"`
	Eikon    string `json:"eikon"`
	Dominant bool   `json:"dominant"`
}
