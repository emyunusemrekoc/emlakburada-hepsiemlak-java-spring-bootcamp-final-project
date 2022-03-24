
# EmlakBurada - Hepsiemlak Java Spring Bitirme Projesi

Bu proje bir emlak sitesi backend projesidir. 

- Kullanıcılar kayıt olabilir.
- Email ve şifresi ile giriş yapabilir. Giriş yapınca token oluşturulur. Bu token ile yetkili olduğu işlemleri gerçekleştirebilir.
- Her kullanıcı kayıt olduktan sonra ilan yayınlama hakkı "0"dır. İlan yayınlaması için paket satın alması gerekir.
- İlan yayınlama hakkı ve paketin geçerlilik süresi kullanıcı entity'sine tanımlanmıştır.
- Paket içeriğinde 10 adet ilan yayınlama hakkı mevcuttur. Kullanım süresi 30 gündür.
- Kullanıcı paket satın almak istediğinde kendi ilan bakiyesi ve kullanım süresi kontrol edilir. Kullanım süresi geçmişse kalan haklar sıfırlanır. Kullanıcı ödemeyi gerçekleştirirse paket rabbitMq üzerinden kullanıcının profiline tanımlanır. 10 adet ilan yayınlama hakkı ve satın aldığı gün baz alınarak 30 gün kullanım süresi eklenir.
- Kullanım süresi geçmemişse bakiye sıfırlanmaz. Ödeme işlemi doğruysa, kalan kullanım haklarının üzerine 10 adet ilan hakkı eklenir, kullanım süresi 30 gün artırılır.
- Satın alma işlemi giriş yapan kullanıcının id'sine göre yapılır. (Bu bilgi header daki tokendan alınır.)
- Kullanıcı ilan oluşturmak istediğinde, ilan hakları ve kullanım süresi kontrol edilir. Paketin süresi geçmişse ya da ilan hakkı yoksa, kalan haklarını sıfırlar. İlan yayınlama işlemi iptal olur. Kullanıcıya hata mesajı döner. Kullanım süresi geçmemişse ve ilan hakkı varsa ilan oluşturulur. Kullanıcının bakiyesinden düşülür.
- İlan oluşturulduğunda ilan statüsü IN_REVIEW 'dir. Kullanıcı ilanın statüsünü başka bir servisten ACTIVE ya da PASSIVE duruma getirebilir.
- İlanların süresi 30 gündür.
### Aşağıdaki işlemler sadece giriş yapan yetkili kullanıcı tarafından yapılabilir:

- Kullanıcı kendi bakiye ve kullanım süresi kontrolünü yapabilir. Profil detaylarına bakabilir.
- Kullanıcı ilan oluşturabilir, düzenleyebilir, silebilir, detaylarına ulaşabilir.
- Kendi ilanlarını aktif ve pasif duruma getirebilir.
- Satın alma işlemi yapabilir, satın aldığı paketlere bakabilir.




### Gerekli açıklamalar proje içindeki methodların üzerinde yapılmıştır.

## UML Diagram

foto eklenecek

6 microservice bulunmaktadır. Hepsi birbiri ile iletişim halindedir. 6 microservice gateway'den yönetilmektedir. Service discovery tarafından instance yönetimi sağlanmaktadır.
- Advert statüsü değiştirme işlemi AdvertService tarafından queue'ya eklenip, AdvertStatusService'e asenkron olarak yaptırılmaktadır.
- Paket tanımlama işlemi PurchaseService tarafından queue'ya eklenip UserService'e asenkron yaptırılmaktadır.

## API Kullanımı

link eklenecek

### individual-user controller
foto
### advert controller
foto
### advertstatus controller
foto
### purchase controller
foto
### payment controller
foto
### auth controller
foto

## Queue
  
## Coverage


## Kullanılan Teknolojiler

- Service Discovery
- Feign Client
- RabbitMQ
- PostgreSQL
- Modelmapper
- Lombok
- Spring Boot Web
- Hibernate
- Spring Data Jpa
- Swagger (OpenApi)

  
## Teşekkür

- Bize eğitim boyunca önemli bilgiler veren, sektörden örnekler sunan eğitmenimiz Cem DİRMAN'a teşekkürler.
- Bootcamp için Hepsiemlak ve Patika.dev' e teşekkürler.

  