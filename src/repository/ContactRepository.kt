package repository

import entity.ContactEntity

class ContactRepository {

    companion object{
        private val contactList = mutableListOf<ContactEntity>()
        fun delete(contact: ContactEntity){
            var index = 0
            for(item in contactList.withIndex()){
                if(item.value.name == contact.name && item.value.telefone == contact.telefone){
                    index = item.index
                    break
                }
            }
            contactList.removeAt(index)
        }

        fun save(contact: ContactEntity){
            contactList.add(contact)
        }

        fun getList(): List<ContactEntity>{
            return contactList
        }
    }

}