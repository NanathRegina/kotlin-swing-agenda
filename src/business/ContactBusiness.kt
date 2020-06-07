package business

import entity.ContactEntity
import repository.ContactRepository

class ContactBusiness {

    fun save(name: String, telefone: String){
        validate(name, telefone)
        val contact = ContactEntity(name, telefone)
        ContactRepository.save(contact)
    }

    private fun validate(name: String, telefone: String){
        if(name == ""){
            throw Exception("Nome é obrigatório")
        }
        if(telefone == ""){
            throw Exception("Telefone é obrigatório")
        }
    }

    private fun validateDelete(name: String, telefone: String){
        if(name == "" || telefone == ""){
            throw Exception("É necessário selecionar um contato")
        }
    }

    fun delete(name: String, telefone: String){
        validateDelete(name, telefone)
        val contact = ContactEntity(name, telefone)
        ContactRepository.delete(contact)
    }

    fun getList(): List<ContactEntity>{
        return ContactRepository.getList()
    }

    fun getContactCountDescription(): String{
        val list = getList()
        return when{
            list.isEmpty() -> "0 contatos"
            list.size == 1 -> "1 contato"
            else -> "${list.size} contatos"
        }

    }
}