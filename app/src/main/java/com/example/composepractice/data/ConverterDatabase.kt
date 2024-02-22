package com.example.composepractice.data

@Database(entities = [ConversionResult::class],version = 1)
abstract class ConverterDatabase : RoomDatabase() {

    abstract val converterDAO : ConverterDao

    companion object{
        @Volatile
        private var INSTANCE : ConverterDatabase? = null
        fun getInstance(context: Context):ConverterDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance==null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        ConverterDatabase::class.java,
                        "converter_data_database"
                    ).build()
                }
                return instance
            }
        }

    }
}